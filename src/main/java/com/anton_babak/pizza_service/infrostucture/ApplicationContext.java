package com.anton_babak.pizza_service.infrostucture;

public interface ApplicationContext {
	
	public Object getBean(String bean) throws Exception;
}
