package com.vcube.smart_mart.service;

import com.vcube.smart_mart.dto.PaymentRequest;
import com.vcube.smart_mart.entity.Payment;

public interface PaymentService {

	Payment makePayment(Long orderId, PaymentRequest request);

	Payment getPaymentByOrder(Long orderId);

	Payment getPaymentById(Long paymentId);

}
