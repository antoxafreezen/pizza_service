package com.anton_babak.pizza_service.domain;

import org.springframework.beans.factory.FactoryBean;

public class Customer implements FactoryBean<Customer>{
	private Integer id;
	private String name;
	private String address;
	private AccumulativeCard card;
	
	public static class AccumulativeCard{
		private Double cash;
		private boolean active;

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		public AccumulativeCard() {
			cash = 0.;
			active = false;
		}

		public void setCash(Double cash){
			this.cash = cash;
		}
		
		public Double getCash() {
			return cash;
		}

		public void addCash(Double addCash){
			cash += addCash;
		}

		@Override
		public String toString() {
			return "AccumulativeCard{" +
					"cash=" + cash + '}';
			}
		}
	
	public Customer(){
		this.card = new AccumulativeCard();
	}
	
	public Customer(Integer id, String name, String address){
		this.id = id;
		this.name = name;
		this.address = address;
		this.card = new AccumulativeCard();
	}
	
	public Customer(Integer id, String name){
		this.id = id;
		this.name = name;
		this.card = new AccumulativeCard();
	}
	
	public void activateAccCard(){
		card.active = true;
	}
	
	public void addOrderCostToAccCard(Order order){
		card.addCash(order.getFinalOrderCost());
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public AccumulativeCard getCard() {
		return card;
	}

	public void setCard(AccumulativeCard card) {
		this.card = card;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public Customer getObject() throws Exception { // call instead of constructor
		// TODO Auto-generated method stub
		return new Customer(1, "Customer 1");
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Customer.class;
	}

	@Override
	public boolean isSingleton() { // isSingleton == false -> Prototype
		// TODO Auto-generated method stub
		return false;
	}


}
