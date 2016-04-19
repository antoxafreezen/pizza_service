package com.anton_babak.pizza_service.infrostucture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationInvocationHandler implements InvocationHandler{

	private Object bean;
	private Class<?> clazz;
	
	
	
	public AnnotationInvocationHandler(Object bean) {
		super();
		this.bean = bean;
		this.clazz = bean.getClass();
	}



	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?>[] parameters = method.getParameterTypes();
		if (clazz.getMethod(method.getName(), parameters).getAnnotation(Benchmark.class) != null) {
			return calculateRunTime(method, args);
		} else
			return method.invoke(bean, args);
	}



	private Object calculateRunTime(Method method, Object[] args)
			throws IllegalAccessException, InvocationTargetException {
		Long a = System.nanoTime();
		Object o = method.invoke(bean, args);
		System.out
				.println("Runtime for method " + method.getName() + ": " + (System.nanoTime() - a));
		return o;
	}
	

}
