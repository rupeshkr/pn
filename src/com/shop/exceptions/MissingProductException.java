/**
 * 
 */
package com.shop.exceptions;

/**
 * @author RR
 *
 */
public class MissingProductException extends Exception {

	private static final long serialVersionUID = -3035230535313018108L;

	public MissingProductException(String message) {
		super(message);
	}
}
