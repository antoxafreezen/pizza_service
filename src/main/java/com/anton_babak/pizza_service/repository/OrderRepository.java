package com.anton_babak.pizza_service.repository;

import com.anton_babak.pizza_service.domain.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}
