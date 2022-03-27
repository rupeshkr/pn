/**
 * 
 */
package com.shop.service;

import static com.shop.entity.User.getUser;
import static java.lang.ThreadLocal.withInitial;

import com.shop.entity.Business;
import com.shop.entity.User;
import com.shop.exceptions.InvalidUserException;

/**
 * Service to return currently logged user and business - currently created for demo, but would actually come from other systems like request parameters and/or DB, etc.
 * 
 * @author RR
 *
 */
public class UserService {

	// to simulate a login for demo app
	protected static ThreadLocal<String> userName = withInitial(() -> "defaultUser");
	protected static ThreadLocal<String> businessName = withInitial(() -> "defaultBusiness");

	public static User getCurrentUser() throws InvalidUserException {
		return getUser(userName.get());
	}

	public static Business getCurrentBusiness() throws InvalidUserException {
		return new Business(businessName.get());
	}
}
