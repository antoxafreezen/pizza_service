package com.anton_babak.pizza_service.infrostucture;

public class ServiceLocator {
	
	private Config config = new JavaConfig();
	
	private final static ServiceLocator instance = new ServiceLocator();
	
	private ServiceLocator(){
		
	}
	
	public static ServiceLocator getInstance(){
		return instance;
	}
	
	public Object lookup(String bean){
		Class<?> clazz = config.getImpl(bean);
		if (clazz == null){
			throw new RuntimeException("Bean not found");
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
