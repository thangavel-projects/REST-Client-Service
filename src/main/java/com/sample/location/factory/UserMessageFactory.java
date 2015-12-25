package com.sample.location.factory;

import com.sample.location.service.UserMessage;
import com.sample.location.service.impl.UserMessageDE;
import com.sample.location.service.impl.UserMessageFR;
import com.sample.location.service.impl.UserMessageIT;
import com.sample.location.service.impl.UserMessageUS;

/**
 * The <code>UserMessageFactory</code> class represents 
 * the Factory design patterns and returns country locale instance 
 * to display the user description message in the console.
 * 
 * @author Thangavel Loganathan
 * 
 */

public class UserMessageFactory {

	/**
	 * The <code>getCountryLocaleByCode</code> method accepts the 
	 * country code and returns the specific country locale instance
	 * based on the code.
	 * 
	 * @param countryCode
	 * @return UserMessage
	 * 
	 */
	
	public static UserMessage getCountryLocaleByCode(String countryCode){
		
		UserMessage userMessage = null;

		if("US".equalsIgnoreCase(countryCode)){
			userMessage = new UserMessageUS();
		}else if ("DE".equalsIgnoreCase(countryCode)){
			userMessage = new UserMessageDE();
		}else if ("FR".equalsIgnoreCase(countryCode)){
			userMessage = new UserMessageFR();
		}else if ("IT".equalsIgnoreCase(countryCode)){
			userMessage = new UserMessageIT();
		}
		return userMessage;
	}
	
}
