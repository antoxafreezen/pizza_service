package com.anton_babak.pizza_service.infrostucture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.util.ClassUtils;

public class BenchmarkBeanProxy {
	
	private Object bean;
	private Class<?> clazz;
	
	
	
	public BenchmarkBeanProxy(Object bean) {
		super();
		this.bean = bean;
		this.clazz = bean.getClass();
	}

	Object createBeanProxy() throws Exception {
		Method[] methods = clazz.getMethods();
		if (isNecessaryToCreateProxy(methods)) {
			InvocationHandler handler = new AnnotationInvocationHandler(bean);
			Class<?>[] interfaces = getAllInterfaces(bean);
			return Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, handler);
		}
		return bean;
	}

	private Boolean isNecessaryToCreateProxy(Method[] methods) {
		Boolean needCreateProxy = false;
		for (Method method : methods) {
			Benchmark benchmark = method.getAnnotation(Benchmark.class);
			if (benchmark != null && benchmark.active()) {
				needCreateProxy = true;
				break;
			}
		}
		return needCreateProxy;
	}

	private static Class<?>[] getAllInterfaces(Object obj) {
        return ClassUtils.getAllInterfaces(obj);       
    }
}
