package com.anton_babak.pizza_service.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.anton_babak.pizza_service.domain.Order;

public class InMemOrderRepositoryTest {
	
	InMemOrderRepository or;
	
	@Before
	public void setUp(){
		or = new InMemOrderRepository();
	}
	
	@Test
	public void saveOrder(){
		Order order = new Order();
		Long orderId = or.saveOrder(order);
		assertEquals(order.getId(), orderId);
		assertEquals(true, or.getOrders().contains(order));
	}
}
