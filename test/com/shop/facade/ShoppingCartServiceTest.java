package com.shop.facade;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.shop.entity.Cart;
import com.shop.entity.CreditCard;
import com.shop.entity.Product;
import com.shop.exceptions.InvalidPaymentModeException;
import com.shop.exceptions.InvalidQuantityException;

public class ShoppingCartServiceTest {

	private ShoppingCartService service = new ShoppingCartService();

	@Test
	public void testSingleProductGoldCard() throws Exception {
		Cart cart = service.createShoppingCart();
		cart.add(new Product("Prod1", BigDecimal.TEN));
		service.addCard(cart, new CreditCard("Gold"));
		assertTrue(service.getTotalPayableAmount(cart).equals(BigDecimal.valueOf(8.0)));
	}
	
	@Test
	public void testMultipleProductSilverCard() throws Exception {
		Cart cart = service.createShoppingCart();
		cart.add(new Product("Prod1", BigDecimal.TEN), 2);
		cart.add(new Product("Prod2", BigDecimal.ONE), 10);
		service.addCard(cart, new CreditCard("Silver"));
		assertTrue(service.getTotalPayableAmount(cart).equals(BigDecimal.valueOf(27.0)));
	}
	
	@Test
	public void testMultipleProductUnknownCardType() throws Exception {
		Cart cart = service.createShoppingCart();
		cart.add(new Product("Prod1", BigDecimal.TEN), 2);
		cart.add(new Product("Prod2", BigDecimal.ONE), 10);
		service.addCard(cart, new CreditCard("NewOne"));
		assertTrue(service.getTotalPayableAmount(cart).equals(BigDecimal.valueOf(30)));
	}
	
	@Test
	public void testAddMultipleProducts() throws Exception {
		Cart cart = service.createShoppingCart();
		cart.add(new Product("Prod1", BigDecimal.TEN), 2);
		cart.add(new Product("Prod2", BigDecimal.ONE), 10);
		service.addCard(cart, new CreditCard("Gold"));
		assertTrue(service.getTotalPayableAmount(cart).equals(BigDecimal.valueOf(24.0)));
		cart.add(new Product("Prod3", BigDecimal.ONE), 5);
		assertTrue(service.getTotalPayableAmount(cart).equals(BigDecimal.valueOf(28.0)));
	}
	
	@Test
	public void testEmptyCart() throws Exception {
		Cart cart = service.createShoppingCart();
		service.addCard(cart, new CreditCard("NewOne"));
		assertTrue(service.getTotalPayableAmount(cart).equals(BigDecimal.ZERO));
	}
	
	@Test(expected = InvalidPaymentModeException.class)
	public void testNoCard() throws Exception {
		Cart cart = service.createShoppingCart();
		cart.add(new Product("Prod1", BigDecimal.TEN), 2);
		assertTrue(service.getTotalPayableAmount(cart).equals(BigDecimal.valueOf(30)));
	}
	
	@Test(expected = InvalidQuantityException.class)
	public void testAddNegativeProducts() throws Exception {
		Cart cart = service.createShoppingCart();
		cart.add(new Product("Prod1", BigDecimal.TEN), -2);
	}
	
	@Test(expected = InvalidQuantityException.class)
	public void testRemoveUnknownProducts() throws Exception {
		Cart cart = service.createShoppingCart();
		cart.add(new Product("Prod1", BigDecimal.TEN), -2);
		cart.remove(new Product("Prod2", BigDecimal.TEN), -2);
	}

}
