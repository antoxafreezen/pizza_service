package com.anton_babak.pizza_service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Pizza {
	
	@Id
	@Column(name = "pizza_id")
	private Integer id;
	@Column(name = "pizza_name")
	private String name;
	@Column(name = "pizza_price")
	private Double price;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "pizza_type")
	private PizzaType type;
	
	public Pizza(){
		
	}
	
	public Pizza(Integer id, String name, Double price, PizzaType type){
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public Pizza(String name, Double price, PizzaType type){
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}

	 @Override
	public String toString() {
		return "Pizza [id=" + id + ", name=" + name + ", price=" + price + ", type=" + type + "]";
	}

	public enum PizzaType{
		VEGETARIAN, SEA, MEAT;
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
		Pizza other = (Pizza) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
