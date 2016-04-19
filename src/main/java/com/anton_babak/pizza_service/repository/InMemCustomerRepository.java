package com.anton_babak.pizza_service.repository;

import java.util.ArrayList;
import java.util.List;

import com.anton_babak.pizza_service.domain.Customer;

public class InMemCustomerRepository implements CustomerRepository{

	List<Customer> customers;
	
	public InMemCustomerRepository() {
		customers = new ArrayList<>();
	}

	@Override
	public Customer checkCustomer(Customer customer) {
		for (Customer c: customers){
			if (c.getId().equals(customer.getId())){
				return customer;
			}
		}
		customers.add(customer);
		return customer;
	}
	
}
