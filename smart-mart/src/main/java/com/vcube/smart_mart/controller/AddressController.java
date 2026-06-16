package com.vcube.smart_mart.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcube.smart_mart.entity.Address;
import com.vcube.smart_mart.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AddressController {

	private final AddressService addressServiceIn;

	@PostMapping("/{userId}")
	public Address addAddress(@PathVariable Long userId, @RequestBody Address address) {

		return addressServiceIn.addAddress(userId, address);
	}

	@GetMapping("/{userId}")
	public List<Address> getAddresses(@PathVariable Long userId) {

		return addressServiceIn.getUserAddresses(userId);
	}
}