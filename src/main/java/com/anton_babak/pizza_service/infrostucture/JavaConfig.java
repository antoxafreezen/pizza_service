package com.anton_babak.pizza_service.infrostucture;

import java.util.HashMap;
import java.util.Map;

import com.anton_babak.pizza_service.repository.InMemOrderRepository;
import com.anton_babak.pizza_service.repository.pizza_rep.InMemPizzaRepository;
import com.anton_babak.pizza_service.service.SimpleOrderService;

public class JavaConfig implements Config{

	private Map<String, Class<?>> beans = new HashMap<>();
	
	{
		beans.put("orderRepository", InMemOrderRepository.class);
		beans.put("pizzaRepository", InMemPizzaRepository.class);
		beans.put("orderService", SimpleOrderService.class);
	}
	
	@Override
	public Class<?> getImpl(String bean) {
		return beans.get(bean);
	}

}
