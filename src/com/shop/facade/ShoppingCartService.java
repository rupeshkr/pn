/**
 * 
 */
package com.shop.facade;

import static com.shop.entity.Cart.getNewInstance;
import static com.shop.service.UserService.getCurrentBusiness;
import static com.shop.service.UserService.getCurrentUser;

import java.math.BigDecimal;

import com.shop.entity.Cart;
import com.shop.entity.CreditCard;
import com.shop.entity.Product;
import com.shop.exceptions.InvalidBusinessException;
import com.shop.exceptions.InvalidPaymentModeException;
import com.shop.exceptions.InvalidQuantityException;
import com.shop.exceptions.InvalidUserException;
import com.shop.exceptions.MissingProductException;
import com.shop.service.PaymentService;

/**
 * Controller/facade class for managing the shopping cart experience
 * @author RR
 *
 */
public class ShoppingCartService {

	private PaymentService paymentService = new PaymentService();

	public Cart createShoppingCart() throws InvalidUserException, InvalidBusinessException {
		return getNewInstance(getCurrentUser(), getCurrentBusiness());
	}

	public Cart addCard(Cart cart, CreditCard card) {
		cart.setCard(card);
		return cart;
	}
	
	public Cart addProduct(Cart cart, Product product, int num) throws InvalidQuantityException {
		return cart.add(product, num);
	}
	
	public Cart removeProduct(Cart cart, Product product, int num) throws MissingProductException, InvalidQuantityException {
		return cart.remove(product, num);
	}

	public BigDecimal getTotalPayableAmount(Cart cart) throws InvalidQuantityException, InvalidPaymentModeException {
		return paymentService.calculatePayableAmount(cart);
	}
}
