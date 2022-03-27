/**
 * 
 */
package com.shop.exceptions;

/**
 * @author RR
 *
 */
public class InvalidPaymentModeException extends Exception {
	private static final long serialVersionUID = -5426451148238295189L;

	public InvalidPaymentModeException(String message) {
		super(message);
	}

}
