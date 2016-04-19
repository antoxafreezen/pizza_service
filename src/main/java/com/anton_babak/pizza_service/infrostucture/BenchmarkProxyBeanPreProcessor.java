package com.anton_babak.pizza_service.infrostucture;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BenchmarkProxyBeanPreProcessor implements BeanPostProcessor{

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		try {
			return new BenchmarkBeanProxy(bean).createBeanProxy();
		} catch (Exception e) {
			return bean;
		}
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
}
