package com.anton_babak.pizza_service.service;

import com.anton_babak.pizza_service.domain.Customer;
import com.anton_babak.pizza_service.domain.Order;

public interface OrderService {

	Order placeNewOrder(Customer customer, Integer... pizzasID);

	void addPizzasToOrder(Order order, Integer pizzaID, Integer count);
	
}