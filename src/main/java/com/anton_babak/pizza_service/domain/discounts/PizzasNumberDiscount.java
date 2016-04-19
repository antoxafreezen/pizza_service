package com.anton_babak.pizza_service.domain.discounts;

import java.util.List;

import com.anton_babak.pizza_service.domain.Order;
import com.anton_babak.pizza_service.domain.Pizza;

public class PizzasNumberDiscount implements Discountable{
	
	private final static Double PIZZA_NUMBER_DISCOUNT = 0.3;
	
	@Override
	public Double calculateDiscount(Order order) {
		List<Pizza> pizzas = order.getPizzas();
		if (pizzas.size() > 4){
			Double maxPizzaPrice = - 1D;
			for (Pizza pizza: pizzas){
				maxPizzaPrice = Double.max(pizza.getPrice(), maxPizzaPrice);
			}
			return maxPizzaPrice * PIZZA_NUMBER_DISCOUNT;
		}
		return 0D;
	}

}
