package com.anton_babak.pizza_service.domain.discounts;

import com.anton_babak.pizza_service.domain.Order;

public interface Discountable {
	Double calculateDiscount(Order order);
}
