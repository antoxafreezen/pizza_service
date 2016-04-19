package com.anton_babak.pizza_service.domain;

import java.util.ArrayList;
import java.util.List;

import com.anton_babak.pizza_service.domain.discounts.Discountable;

public class Order {
	
	
	private static Long countID = 0l;
	private Long id;
	private Customer customer;
	private List<Pizza> pizzas;
	private OrderStatus status;
	private Double orderCost;
	private Boolean paid = false;
	private List<Discountable> discounts;

	public Order(){
		this.pizzas = new ArrayList<>();
		this.discounts = new ArrayList<>();
		this.status = OrderStatus.NEW;
		this.orderCost = 0.0;
		this.id = countID++;
	}
	
	public Order(Customer customer, List<Pizza> pizzas){
		this.customer = customer;
		this.pizzas = pizzas;
		this.id = countID++;
		this.status = OrderStatus.NEW;
		this.orderCost = 0.0;
		this.discounts = new ArrayList<>();
	}
	
	public Double getFinalOrderCost(){
		for (Pizza pizza: pizzas){
			Double price = pizza.getPrice();
			orderCost += price;
		}
		Double cost = orderCost.doubleValue();
		for (Discountable discount : discounts){
			cost -= discount.calculateDiscount(this);
		}
		orderCost = cost;
		return orderCost;
	}
	
	public void addPizza(Pizza pizza){
		pizzas.add(pizza);
	}
	public boolean edit(){
		if (status.equals(OrderStatus.CANCELED)){
			status = OrderStatus.NEW;
			return true;
		}
		return false;
	}
	
	public boolean inProgress(){
		if (status.equals(OrderStatus.NEW)){
			status = OrderStatus.IN_PROGRESS;
			return true;
		}
		return false;
	}
	
	public boolean cancel(){
		if (status.equals(OrderStatus.IN_PROGRESS) ){
			status = OrderStatus.CANCELED;
			return true;
		}
		return false;
	}
	
	public boolean done(){
		if (status.equals(OrderStatus.IN_PROGRESS)){
			status = OrderStatus.DONE;
			customer.addOrderCostToAccCard(this);
			paid = true;
			return false;
		}
		return true;
	}
	public void addDiscount(Discountable discount){
		discounts.add(discount);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public Double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(Double orderCost) {
		this.orderCost = orderCost;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", customer=" + customer + ", pizzas=" + pizzas + "]";
	}
	
	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Boolean IsPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public enum OrderStatus{
		NEW, IN_PROGRESS, CANCELED, DONE;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
