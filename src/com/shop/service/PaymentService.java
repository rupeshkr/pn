/**
 * 
 */
package com.shop.service;

import java.math.BigDecimal;

import com.shop.entity.Cart;
import com.shop.entity.Product;
import com.shop.exceptions.InvalidPaymentModeException;
import com.shop.exceptions.InvalidQuantityException;

/**
 * @author RR
 *
 */
public class PaymentService {

	private DiscountService discountService = new DiscountService();

	public BigDecimal calculatePayableAmount(Cart cart) throws InvalidQuantityException, InvalidPaymentModeException {
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (Product product : cart.getProductsInCart()) {
			Integer quantity = cart.getQuantityOfProductInCart(product).orElseThrow(() -> new InvalidQuantityException(String.format("Unknown number of products '%s' in cart", product.getName())));
			BigDecimal productAmount = product.getPrice().multiply(BigDecimal.valueOf(quantity));
			totalAmount = totalAmount.add(productAmount);
		}
		BigDecimal discountAmount = totalAmount.multiply(discountService.calculateDiscountPercentage(cart).divide(BigDecimal.valueOf(100)));
		
		return totalAmount.subtract(discountAmount);
	}
}
