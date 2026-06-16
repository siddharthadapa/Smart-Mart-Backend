package com.vcube.smart_mart.service;

import java.util.List;

import com.vcube.smart_mart.entity.Address;

public interface AddressService {

	Address addAddress(Long userId, Address address);

	List<Address> getUserAddresses(Long userId);
}
