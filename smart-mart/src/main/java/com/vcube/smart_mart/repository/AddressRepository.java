package com.vcube.smart_mart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vcube.smart_mart.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

	List<Address> findByUserId(Long userId);

}
