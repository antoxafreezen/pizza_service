package com.anton_babak.pizza_service.service;

import com.anton_babak.pizza_service.domain.Pizza;

public class CreatePizzaService {
	
	public Pizza createPizza(Integer id, String name, Double price, Pizza.PizzaType type){
		return new Pizza(id, name, price, type);
	}
	
}
