package com.vcube.smart_mart.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vcube.smart_mart.dto.PaymentRequest;
import com.vcube.smart_mart.entity.Order;
import com.vcube.smart_mart.entity.Payment;
import com.vcube.smart_mart.repository.OrderRepository;
import com.vcube.smart_mart.repository.PaymentRepository;
import com.vcube.smart_mart.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	private final OrderRepository orderRepository;

	@Override
	public Payment makePayment(Long orderId, PaymentRequest request) {

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order Not Found"));

		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setAmount(order.getTotalAmount());
		payment.setPaymentDate(LocalDateTime.now());

		String method = request.getPaymentMethod().toUpperCase();
		payment.setPaymentMethod(method);

		// Handle specific conditional assignments based on payment option selections
		if ("COD".equals(method)) {
			payment.setPaymentStatus("PENDING_ON_DELIVERY");
			payment.setTransactionId("COD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
			order.setStatus("AWAITING_DELIVERY");
		} else if ("GIFT_CARD".equals(method)) {
			payment.setPaymentStatus("SUCCESS");

			// ✅ Null safety check: Fallback to generic code if frontend sends an empty
			// string
			String code = (request.getVoucherCode() != null) ? request.getVoucherCode().toUpperCase() : "DEFAULT";
			payment.setTransactionId("VOUCHER-" + code);
			order.setStatus("PAID");
		} else if ("UPI".equals(method)) {
			payment.setPaymentStatus("SUCCESS");
			payment.setTransactionId(
					"UPI-" + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 10));
			order.setStatus("PAID");
		} else if ("CARD".equals(method)) {
			payment.setPaymentStatus("SUCCESS");
			payment.setTransactionId(
					"CRD-" + UUID.randomUUID().toString().replace("-", "").toUpperCase().substring(0, 10));
			order.setStatus("PAID");
		} else {
			throw new RuntimeException("Invalid Payment Method Selection: " + method);
		}

		// Save updated states safely back to your data tiers
		orderRepository.save(order);
		return paymentRepository.save(payment);
	}

	@Override
	public Payment getPaymentByOrder(Long orderId) {
		return paymentRepository.findByOrderId(orderId).orElseThrow(() -> new RuntimeException("Payment Not Found"));
	}

	@Override
	public Payment getPaymentById(Long paymentId) {
		return paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment Not Found"));
	}
}