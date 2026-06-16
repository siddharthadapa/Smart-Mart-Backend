package com.vcube.smart_mart.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vcube.smart_mart.entity.Address;
import com.vcube.smart_mart.entity.User;
import com.vcube.smart_mart.repository.AddressRepository;
import com.vcube.smart_mart.repository.UserRepository;
import com.vcube.smart_mart.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;

	private final UserRepository userRepository;

	@Override
	public Address addAddress(Long userId, Address address) {

		User user = userRepository.findById(userId)
				                  .orElseThrow(() -> 
				                   new RuntimeException("User Not Found"));

		address.setUser(user);

		return addressRepository.save(address);
	}

	@Override
	public List<Address> getUserAddresses(Long userId) {

		return addressRepository.findByUserId(userId);
	}

}
