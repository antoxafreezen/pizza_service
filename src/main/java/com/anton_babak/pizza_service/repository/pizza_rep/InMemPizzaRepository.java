package com.anton_babak.pizza_service.repository.pizza_rep;

import java.util.ArrayList;
import java.util.List;

import com.anton_babak.pizza_service.domain.Pizza;
import com.anton_babak.pizza_service.domain.Pizza.PizzaType;
import com.anton_babak.pizza_service.infrostucture.Benchmark;
import com.anton_babak.pizza_service.infrostucture.PostConstruct;

public class InMemPizzaRepository implements PizzaRepository{

	private List<Pizza> pizzas;
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public InMemPizzaRepository() {
		pizzas = new ArrayList<>();
	}
	
	@PostConstruct
	public void cookPizzas(){
//		pizzas.add(new Pizza(1, "Pizza 1", 30.0, PizzaType.VEGETARIAN));
//		pizzas.add(new Pizza(2, "Pizza 2", 10.0, PizzaType.MEAT));
//		pizzas.add(new Pizza(3, "Pizza 3", 10.0, PizzaType.SEA));
	}
	
	public void init(){
		System.out.println("Init1");
	}
	
	@Override
	@Benchmark
	public Pizza getPizzaByID(Integer id) {
		for (Pizza pizza: pizzas){
			if (pizza.getId().equals(id)){
				return pizza;
			}
		}
		return null;
	}

	@Override
	public void createPizza(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePizza(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePizza(Pizza pizza) {
		// TODO Auto-generated method stub
		
	}

}
