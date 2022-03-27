/**
 * 
 */
package com.shop.service;

import java.math.BigDecimal;

import com.shop.entity.Cart;
import com.shop.exceptions.InvalidPaymentModeException;
import com.shop.secured.CreditCardAdminService;

/**
 * @author RR
 *
 */
public class DiscountService {

	private CreditCardAdminService cardAdminService = new CreditCardAdminService();

	public BigDecimal calculateDiscountPercentage(Cart cart) throws InvalidPaymentModeException {
		if (cart.getCard() == null) {
			throw new InvalidPaymentModeException("No payment mode added in cart.");
		}
		return cardAdminService.getDiscountFor(cart.getCard().getCardType());
	}
}
