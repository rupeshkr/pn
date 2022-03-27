/**
 * 
 */
package com.shop.secured;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller class for managing credit card types and discounts in %
 * @author RR
 *
 */
public class CreditCardAdminService {
	private static Map<String, BigDecimal> cardTypes = new HashMap<>();
	
	public static void registerCardType(String cardType) {
		updateDiscountPercentage(cardType, BigDecimal.ZERO);
	}
	
	/**
	 * @param cardType 
	 * @param discount
	 * @return previous discount associated with this card type
	 */
	public static BigDecimal updateDiscountPercentage(String cardType, BigDecimal discount) {
		return cardTypes.put(cardType, discount);
	}

	static { // for demo
		updateDiscountPercentage("Gold", BigDecimal.valueOf(20));
		updateDiscountPercentage("Silver", BigDecimal.TEN);
		registerCardType("Normal");
	}

	public BigDecimal getDiscountFor(String cardType) {
		return cardTypes.getOrDefault(cardType, BigDecimal.ZERO);
	}
}
