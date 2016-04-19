package com.anton_babak.pizza_service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.anton_babak.pizza_service.domain.Pizza;
import com.anton_babak.pizza_service.domain.Pizza.PizzaType;

public class JPAWithoutSpringApp {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		
		Pizza pizza = new Pizza();
		pizza.setId(1);
		pizza.setName("Pizza 1");
		pizza.setPrice(100.0);
		pizza.setType(PizzaType.MEAT);
		
		try{
			em.getTransaction().begin();;
//			em.persist(pizza);
			em.getTransaction().commit();
			
			Pizza p = em.find(Pizza.class, 1);
			System.out.println(p);
		}
		finally{
			em.close();
			emf.close();
		}
		
	}
}
