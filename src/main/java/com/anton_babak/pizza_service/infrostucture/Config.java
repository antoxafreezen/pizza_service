package com.anton_babak.pizza_service.infrostucture;

public interface Config {
	
	Class<?> getImpl(String bean);
}
