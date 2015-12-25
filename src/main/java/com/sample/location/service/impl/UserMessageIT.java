package com.sample.location.service.impl;

import java.util.Locale;

import com.sample.location.service.UserMessage;

/**
 * The <code>UserMessageIT</code> class represents to Italian Locale
 * to display the local description and it implements <code>UserMessage</code>
 * interface and implements <code>getCountryLocale</code> method to return Italian locale.
 * 
 * @author Thangavel Loganathan
 */

public class UserMessageIT implements UserMessage {

	
	/**
	 * The <code>getCountryLocale</code> method returns 
	 * Italy locale.
	 * 
	 * @return Italy Locale instance.
	 */
	
	public Locale getCountryLocale() {
		return new Locale("it", "IT");
	}

}
