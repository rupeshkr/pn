/**
 * 
 */
package com.shop.entity;

import java.math.BigDecimal;

/**
 * @author RR
 *
 */
public class Product implements Comparable<Product> {

	private final String name;
	private final BigDecimal price;

	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Product o) {
		return this.name.compareTo(o.name);
	}
}
