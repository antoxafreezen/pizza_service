package com.anton_babak.pizza_service.domain.discounts;

import com.anton_babak.pizza_service.domain.Order;

public class CustomerAccCardDiscount implements Discountable {
	
	private final static Double ORDER_COST_DISCOUNT = 0.3;
	private final static Double CARD_DISCOUNT = 0.1;
	
	@Override
	public Double calculateDiscount(Order order) {
		if(order.getCustomer().getCard().isActive()){
			Double maxDiscount = order.getOrderCost() * ORDER_COST_DISCOUNT;
			Double discount = order.getCustomer().getCard().getCash() * CARD_DISCOUNT;
			if (discount > maxDiscount) return maxDiscount;
			return discount;
		}
		return 0D;
	}
	
}
