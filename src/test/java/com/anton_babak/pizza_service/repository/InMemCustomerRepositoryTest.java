package com.anton_babak.pizza_service.repository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.anton_babak.pizza_service.domain.Customer;

public class InMemCustomerRepositoryTest {
	
	CustomerRepository cr;
	
	@Before
	public void setUp(){
		cr = new InMemCustomerRepository();
	}
	
	@Test
	public void checkCustomer(){
		Customer customer = new Customer(1, "Customer 1");
		Customer customer1 = cr.checkCustomer(customer);
		Customer customer2 = cr.checkCustomer(customer);
		boolean actual =  customer1.equals(customer2);
		assertEquals(true, actual);
	}
}
