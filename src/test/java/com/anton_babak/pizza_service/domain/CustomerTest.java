package com.anton_babak.pizza_service.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.anton_babak.pizza_service.domain.Customer.AccumulativeCard;

public class CustomerTest {

	Customer customer;
	
	@Before
	public void setUp(){
		customer = new Customer();
	}
	
	@Test
	public void activateCard(){
		boolean expected = true;
		customer.activateAccCard();
		boolean actual = customer.getCard().isActive();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void addOrderCostToAccCard(){
		Order order = new Order();
		order.setOrderCost(30.0);
		customer.getCard().setCash(10.0);
		customer.addOrderCostToAccCard(order);
		assertEquals(40.0, customer.getCard().getCash(), 0.0);
	}
	
	@Test
	public void addCashToCardCash(){
		AccumulativeCard card = new AccumulativeCard();
		card.addCash(10.0);
		assertEquals(10.0 , card.getCash(), 0.0);
	}
	
}
