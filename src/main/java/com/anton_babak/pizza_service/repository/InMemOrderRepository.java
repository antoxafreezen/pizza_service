package com.anton_babak.pizza_service.repository;

import java.util.ArrayList;
import java.util.List;

import com.anton_babak.pizza_service.domain.Order;

public class InMemOrderRepository implements OrderRepository{

	private List<Order> orders;
	
	public InMemOrderRepository() {
		orders = new ArrayList<>();
	}
	
	@Override
	public Long saveOrder(Order order) {
		orders.add(order);
		return order.getId();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
	
}
