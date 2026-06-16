package com.vcube.smart_mart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

	private String paymentMethod; // COD, UPI, CARD, GIFT_CARD

	// ✅ THESE MUST BE HERE SO request.getVoucherCode() CAN BE RESOLVED
	private String upiId;
	private String cardNumber;
	private String voucherCode;
}