/**
 * 
 */
package com.shop.entity;

import com.shop.exceptions.InvalidUserException;

/**
 * @author RR
 *
 */
public class User {

	private final String userName;
	private CreditCard defaultCard;

	private User(String userName) {
		this.userName = userName;
	}

	public static User getUser(String userName) throws InvalidUserException {
		if (userName == null || userName.trim().length() == 0) {
			throw new InvalidUserException("Empty username provided");
		}

		return new User(userName);
	}

	public String getUserName() {
		return userName;
	}

	public CreditCard getDefaultCard() {
		return defaultCard;
	}

}
