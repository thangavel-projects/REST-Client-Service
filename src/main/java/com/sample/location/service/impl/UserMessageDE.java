package com.sample.location.service.impl;

import java.util.Locale;

import com.sample.location.service.UserMessage;

/**
 * The <code>UserMessageDE</code> class represents to German Locale
 * to display the local description and it implements <code>UserMessage</code>
 * interface and implements <code>getCountryLocale</code> method to return German locale.
 * 
 * @author Thangavel Loganathan
 */

public class UserMessageDE implements UserMessage {


	/**
	 * The <code>getCountryLocale</code> method returns 
	 * German locale.
	 * 
	 * @return Germany Locale instance.
	 */
	
	public Locale getCountryLocale() {
		return new Locale("de", "DE");
	}


}
