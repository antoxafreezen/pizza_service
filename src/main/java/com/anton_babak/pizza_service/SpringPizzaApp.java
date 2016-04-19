package com.anton_babak.pizza_service;



import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.core.env.MutablePropertySources;

import com.anton_babak.pizza_service.domain.Customer;
import com.anton_babak.pizza_service.domain.Order;
import com.anton_babak.pizza_service.domain.Pizza;
import com.anton_babak.pizza_service.domain.Pizza.PizzaType;
import com.anton_babak.pizza_service.repository.pizza_rep.PizzaRepository;
import com.anton_babak.pizza_service.service.OrderService;
import com.anton_babak.pizza_service.service.SimpleOrderService;

public class SpringPizzaApp {
	
	public static void main(String [] args){
		Locale.setDefault(Locale.ENGLISH);
		ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryContext.xml");
		
		/*repContext.getEnvironment().setActiveProfiles("dev");
		repContext.refresh();*/
		
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false);
		
		
		context.setParent(repContext);
		context.refresh();
		
		PizzaRepository pizzaRepository = context.getBean("pizzaRepository", PizzaRepository.class);
		pizzaRepository.createPizza(new Pizza("Pizza 1", 10.0, PizzaType.MEAT));
		pizzaRepository.createPizza(new Pizza("Pizza 2", 20.0, PizzaType.SEA));
		pizzaRepository.createPizza(new Pizza("Pizza 3", 30.0, PizzaType.VEGETARIAN));
		
		System.out.println(pizzaRepository.getPizzaByID(1));
		
		/*OrderService orderService = context.getBean("orderService", OrderService.class);
		//((SimpleOrderService) orderService).setOrder(context);
		
		Customer customer = new Customer();
		Order order = orderService.placeNewOrder(customer, 1, 2);
		System.out.println(order);*/
		
		/*Pizza pizza = context.getBean(Pizza.class);
		System.out.println(pizza);*/
		
		/*Customer customer1 = context.getBean(Customer.class);
		System.out.println(customer1);
		Customer customer2 = context.getBean(Customer.class);
		System.out.println(customer2);
		System.out.println(customer1 == customer2);
		
		ApplicationContext parent = context.getParent();
		System.out.println(parent);*/
		
		context.close();
		repContext.close();
	}
}
