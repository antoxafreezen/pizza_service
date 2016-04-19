package com.anton_babak.pizza_service.infrostucture;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class BeanBuilder {
	private final Class<?> clazz;
	private Object bean;
	private ApplicationContext applicationContext;
	private Object beanProxy;

	public BeanBuilder(
			Class<?> clazz, ApplicationContext applicationContext) {
		super();
		this.applicationContext = applicationContext;
		this.clazz = clazz;
	}

	void createBean() throws Exception {

		Constructor<?> constructor = clazz.getConstructors()[0];
		if (constructor.getParameterCount() == 0) {
			bean = clazz.newInstance();
		} else {
			bean = createNewInstanceWithParams(constructor);
		}

	}

	private Object createNewInstanceWithParams(Constructor<?> constructor)
			throws Exception, InstantiationException, IllegalAccessException, InvocationTargetException {
		Object bean;
		Object[] paramBeans = getParams(constructor);
		bean = constructor.newInstance(paramBeans);
		return bean;
	}

	private Object[] getParams(Constructor<?> constructor) throws Exception {
		Class<?>[] paramTypes = constructor.getParameterTypes();
		Object[] paramBeans = new Object[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			paramBeans[i] = getBeanByType(paramTypes[i]);
		}
		return paramBeans;
	}

	private Object getBeanByType(Class<?> paramType) throws Exception {
		String paramName = getBeanNameByType(paramType);
		return applicationContext.getBean(paramName);
	}

	private String getBeanNameByType(Class<?> paramTypes) throws Exception {
		String paramTypeName = paramTypes.getSimpleName();
		return changeFirstLetterToLowerCase(paramTypeName);
	}

	private String changeFirstLetterToLowerCase(String paramTypeName) {
		return Character.toLowerCase(paramTypeName.charAt(0)) + paramTypeName.substring(1);
	}

	void createBeanProxy() throws Exception {
			bean = new BenchmarkBeanProxy(bean).createBeanProxy();
	}

	void callPostConstructMethod() throws Exception {
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getAnnotation(PostConstruct.class) != null) {
				m.invoke(bean);
			}
		}
	}

	void callInitBuilder() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getName().equals("init"))
				m.invoke(bean);
		}
	}

	Object build() {
		if (beanProxy != null) return beanProxy;
		return bean;
	}
}
