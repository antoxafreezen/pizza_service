package com.anton_babak.pizza_service.repository;

import com.anton_babak.pizza_service.domain.Customer;

public interface CustomerRepository {
	Customer checkCustomer(Customer customer);
}
