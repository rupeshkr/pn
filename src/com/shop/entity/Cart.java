/**
 * 
 */
package com.shop.entity;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import com.shop.exceptions.InvalidBusinessException;
import com.shop.exceptions.InvalidQuantityException;
import com.shop.exceptions.InvalidUserException;
import com.shop.exceptions.MissingProductException;

/**
 * @author RR
 *
 */
public class Cart {

	private final User user;
	private final Business buss;
	private CreditCard card;
	private final Map<Product, Integer> products = new TreeMap<Product, Integer>();

	/**
	 * To create a new cart associated with a give user and business
	 * @param user associated user
	 * @param buss associated business
	 * @return cart
	 * @throws InvalidUserException if invalid user is present in the context
	 * @throws InvalidBusinessException if invalid business is present in the context
	 */
	public static Cart getNewInstance(User user, Business buss) throws InvalidUserException, InvalidBusinessException {
		if (user == null) {
			throw new InvalidUserException("User cannot be null");
		}
		if (buss == null) {
			throw new InvalidBusinessException("Business cannot be null");
		}
		if (user.getDefaultCard() != null) {
			return new Cart(user, buss, user.getDefaultCard());
		}
		return new Cart(user, buss);
	}

	private Cart(User user, Business buss) {
		this(user, buss, null);
	}
	
	private Cart(User user, Business buss, CreditCard card) {
		this.user = user;
		this.buss = buss;
		this.card = card;
	}

	public Cart add(Product product) throws InvalidQuantityException {
		return add(product, 1);
	}
	
	public Cart add(Product product, int num) throws InvalidQuantityException {
		if (num <= 0) {
			throw new InvalidQuantityException("0 or negative number of products cannot be added into cart.");
		}
		Integer quantity = products.get(product);
		quantity = quantity == null ? quantity = Integer.valueOf(num) : Integer.valueOf(quantity.intValue() + num);
		products.put(product, quantity);
		return this;
	}

	public Cart remove(Product product) throws MissingProductException, InvalidQuantityException {
		return remove(product, 1);
	}
	
	public Cart remove(Product product, int num) throws MissingProductException, InvalidQuantityException {
		if (num <= 0) {
			throw new InvalidQuantityException("0 or negative number of products cannot be removed from cart.");
		}
		Integer quantity = products.get(product);
		if (quantity == null || quantity.intValue() <= num-1) {
			throw new MissingProductException("Product not present in the cart");
		} else if (quantity.intValue() == num) {
			products.remove(product);
		} else {
			products.put(product, Integer.valueOf(quantity.intValue() - num));
		}
		return this;
	}

	public Set<Product> getProductsInCart() {
		return Collections.unmodifiableSet(products.keySet());
	}
	
	public Optional<Integer> getQuantityOfProductInCart(Product product) {
		return Optional.ofNullable(products.get(product));
	}

	/**
	 * @return the card
	 */
	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}
}
