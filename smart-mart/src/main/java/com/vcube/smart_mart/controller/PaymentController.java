package com.vcube.smart_mart.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.smart_mart.dto.PaymentRequest;
import com.vcube.smart_mart.entity.Payment;
import com.vcube.smart_mart.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping("/pay/{orderId}")
	public Payment makePayment(@PathVariable Long orderId, @RequestBody PaymentRequest request) {

		return paymentService.makePayment(orderId, request);
	}

	@GetMapping("/order/{orderId}")
	public Payment getPaymentByOrder(@PathVariable Long orderId) {

		return paymentService.getPaymentByOrder(orderId);
	}

	@GetMapping("/{paymentId}")
	public Payment getPaymentById(@PathVariable Long paymentId) {

		return paymentService.getPaymentById(paymentId);
	}
}