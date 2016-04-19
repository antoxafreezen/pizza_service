package com.anton_babak.pizza_service.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.anton_babak.pizza_service.domain.Customer;
import com.anton_babak.pizza_service.domain.Order;

@ContextConfiguration(locations = { 
		"classpath:/appContext.xml" }, 
		inheritInitializers = true
)

public class SimpleOrderServiceTest extends RepositoryTestConfig {

	@Autowired
	OrderService orderService;

	@Test
	public void testPlaceNewOrder() {
		Customer customer = null;
		Integer[] pizzasID = new Integer[] { 1 };
		Order result = orderService.placeNewOrder(customer, pizzasID);
		System.out.println(result);
	}
}
