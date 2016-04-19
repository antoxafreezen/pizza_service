package com.anton_babak.pizza_service;

import com.anton_babak.pizza_service.domain.Customer;
import com.anton_babak.pizza_service.domain.Order;
import com.anton_babak.pizza_service.infrostucture.ApplicationContext;
import com.anton_babak.pizza_service.infrostucture.JavaConfigApplicationContext;
import com.anton_babak.pizza_service.repository.pizza_rep.PizzaRepository;
import com.anton_babak.pizza_service.service.OrderService;

public class PizzaApp {
	public static void main(String[] args) throws Exception {

        Customer customer = new Customer(1, "Customer 1");
        Order order;
        
        ApplicationContext ac = new JavaConfigApplicationContext();
        // Inversion of Control container includes dependency injection(1 function), configuration and lifecycle
        // Configure in IoC
        PizzaRepository pizzaRepository = (PizzaRepository) ac.getBean("pizzaRepository");
        System.out.println(pizzaRepository.getPizzaByID(1));
        
        OrderService orderService = (OrderService) ac.getBean("orderService");
        order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);

}
}
