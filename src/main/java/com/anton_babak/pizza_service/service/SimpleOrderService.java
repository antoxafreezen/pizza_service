package com.anton_babak.pizza_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.anton_babak.pizza_service.domain.Customer;
import com.anton_babak.pizza_service.domain.Order;
import com.anton_babak.pizza_service.domain.Pizza;
import com.anton_babak.pizza_service.infrostucture.Benchmark;
import com.anton_babak.pizza_service.repository.CustomerRepository;
import com.anton_babak.pizza_service.repository.InMemCustomerRepository;
import com.anton_babak.pizza_service.repository.OrderRepository;
import com.anton_babak.pizza_service.repository.pizza_rep.PizzaRepository;

public class SimpleOrderService implements OrderService/*, ApplicationContextAware*/ {
	
	//private ApplicationContext appContext;
	
	/*public ApplicationContext getAppContext() {
		return appContext;
	}

	public void setOrder(ApplicationContext appContext) {
		this.appContext = appContext;
	}*/

	//private ServiceLocator locator = ServiceLocator.getInstance();
	private OrderRepository orderRepository;// = (OrderRepository) locator.lookup("orderRepository");
	private PizzaRepository pizzaRepository;// = (PizzaRepository) locator.lookup("pizzaRepository");
	private CustomerRepository customerRepository = new InMemCustomerRepository();

	public SimpleOrderService(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
		this.orderRepository = orderRepository;
		this.pizzaRepository = pizzaRepository;
	}
	
	@Override
	@Benchmark
	public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
		List<Pizza> pizzas = pizzasByArrOfId(pizzasID);
        Order newOrder = createOrder(/*customerRepository.checkCustomer(customer), pizzas*/);
        
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);
        orderRepository.saveOrder(newOrder);  
        return newOrder;

	}
	
	@Override
	public void addPizzasToOrder(Order order, Integer pizzaID, Integer count) {
		Pizza pizza = pizzaRepository.getPizzaByID(pizzaID);
		for (int i = 0; i < count; i++){
			order.addPizza(pizza);
		}
		
	}
	protected Order createOrder(/*Customer customer, List<Pizza> pizzas*/){return null;}
		//Order newOrder = new Order(customer, pizzas);
		/*Order order = appContext.getBean("order", Order.class);
		return order;*/
	
	private List<Pizza> pizzasByArrOfId(Integer... pizzasID) {
		List<Pizza> pizzas = new ArrayList<>();
        for(Integer id : pizzasID){
                pizzas.add(pizzaRepository.getPizzaByID(id));  
        }
		return pizzas;
	}

	/*@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContext = applicationContext;
		
	}*/
}
