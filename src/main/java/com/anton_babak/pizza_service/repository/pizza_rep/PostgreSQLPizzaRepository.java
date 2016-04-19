package com.anton_babak.pizza_service.repository.pizza_rep;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.anton_babak.pizza_service.domain.Pizza;
import com.anton_babak.pizza_service.domain.Pizza.PizzaType;

@Repository("pizzaRepository")
public class PostgreSQLPizzaRepository implements PizzaRepository{

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public Pizza getPizzaByID(Integer id) {
		Pizza pizza = this.jdbcTemplate.queryForObject(
				"select pizza_name, pizza_price, pizza_type from pizza_service.pizzas where pizza_id = ?;",
				new Object[]{id},
				new RowMapper<Pizza>(){

					@Override
					public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
						Pizza pizza = new Pizza();
						pizza.setId(id);
						pizza.setName(rs.getString("pizza_name"));
						pizza.setPrice(rs.getDouble("pizza_price"));
						String type = rs.getString("pizza_type");
						pizza.setType(PizzaType.valueOf(type.trim()));
						return pizza;
					}
					
				});
		return pizza;
	}

	@Override
	public void createPizza(Pizza pizza){
		jdbcTemplate.update(
				"insert into pizza_service.pizzas (pizza_name, pizza_price, pizza_type) values(?, ?, ?);",
				pizza.getName(), pizza.getPrice(), pizza.getType().name());
	}
	@Override
	public void updatePizza(Pizza pizza){
		jdbcTemplate.update(
				"update pizza_service.pizzas set pizza_name = ?, pizza_price = ?, pizza_type = ?;",
				pizza.getName(), pizza.getPrice(), pizza.getType().toString());;
	}
	@Override
	public void deletePizza(Pizza pizza){
		jdbcTemplate.update(
				"delete from pizza_service.pizzas where pizza_id = ?;",
				pizza.getId());
	}
	
}
