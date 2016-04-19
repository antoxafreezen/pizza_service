package com.anton_babak.pizza_service.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.anton_babak.pizza_service.domain.Order.OrderStatus;
import com.anton_babak.pizza_service.domain.discounts.CustomerAccCardDiscount;
import com.anton_babak.pizza_service.domain.discounts.PizzasNumberDiscount;
import com.anton_babak.pizza_service.repository.pizza_rep.InMemPizzaRepository;
import com.anton_babak.pizza_service.repository.pizza_rep.PizzaRepository;
@RunWith(MockitoJUnitRunner.class)
public class OrderTest {
	
	
	@Mock
	Customer customer;
	
	Order order;
	
	@Before
	public void setUp(){
		order = new Order();
		order.setCustomer(customer);
	}
	
	@Test
	public void addPizzaToOrder(){
		order = mock(Order.class);
		order.addPizza(new Pizza());
		verify(order, times(1)).addPizza(any(Pizza.class));
	}
	
	@Test
	public void addPizza(){
		order.addPizza(new Pizza());
		int actualSize = order.getPizzas().size();
		int expectedSize = 1;
		assertEquals(expectedSize, actualSize);
	}
	
	@Test
	public void editOrderShouldChangeStatusToNew(){
		order.setStatus(OrderStatus.CANCELED);
		order.edit();
		assertEquals(order.getStatus(), OrderStatus.NEW);
	}
	
	@Test
	public void editOrderShouldNotChangeStatus(){
		order.setStatus(OrderStatus.NEW);
		order.edit();
		assertEquals(order.getStatus(), OrderStatus.NEW);
	}
	
	@Test
	public void doneOrderShouldChangeStatusToDone(){
		order.setStatus(OrderStatus.IN_PROGRESS);
		order.done();
		assertEquals(order.getStatus(), OrderStatus.DONE);
	}
	
	@Test
	public void doneOrderShouldNotChangeStatus(){
		order.setStatus(OrderStatus.DONE);
		order.done();
		assertEquals(order.getStatus(), OrderStatus.DONE);
	}
	
	@Test
	public void cancelOrderShouldChangeStatusToCanceled(){
		order.setStatus(OrderStatus.IN_PROGRESS);
		order.cancel();
		assertEquals(order.getStatus(), OrderStatus.CANCELED);
	}
	
	@Test
	public void cancelOrderShouldNotChangeStatus(){
		order.setStatus(OrderStatus.CANCELED);
		order.cancel();
		assertEquals(order.getStatus(), OrderStatus.CANCELED);
	}
	
	@Test
	public void inProgressOrderShouldChangeStatusToInProgress(){
		order.setStatus(OrderStatus.NEW);
		order.inProgress();
		assertEquals(order.getStatus(), OrderStatus.IN_PROGRESS);
	}
	
	@Test
	public void inProgressOrderShouldNotChangeStatus(){
		order.setCustomer(customer);
		order.setStatus(OrderStatus.IN_PROGRESS);
		order.inProgress();
		assertEquals(order.getStatus(), OrderStatus.IN_PROGRESS);
	}
	
	@Test
	public void addDiscountableObjectToOrder(){
		order.addPizza(new Pizza());
		int actualSize = order.getPizzas().size();
		int expectedSize = 1;
		assertEquals(expectedSize, actualSize);
	}
	@Ignore
	@Test
	public void calculateFinalOrderCost(){
		PizzaRepository pr = new InMemPizzaRepository();
		order.addPizza(pr.getPizzaByID(1));
		order.addPizza(pr.getPizzaByID(2));
		order.addPizza(pr.getPizzaByID(3));
		order.addPizza(pr.getPizzaByID(3));
		order.addPizza(pr.getPizzaByID(3));
		order.addDiscount(new CustomerAccCardDiscount());
		order.addDiscount(new PizzasNumberDiscount());
		Customer customer = new Customer();
		customer.getCard().setCash(10.0);
		customer.activateAccCard();
		order.setCustomer(customer);
		Double actualCost = order.getFinalOrderCost();
		Double expectedCost = 60.0;
		assertEquals(expectedCost, actualCost, 0.0);
	}
}
