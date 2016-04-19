package com.anton_babak.pizza_service.repository.pizza_rep;

import com.anton_babak.pizza_service.domain.Pizza;

public interface PizzaRepository {
	
	void createPizza(Pizza pizza);
	void updatePizza(Pizza pizza);
	void deletePizza(Pizza pizza);
	Pizza getPizzaByID(Integer id);
}
