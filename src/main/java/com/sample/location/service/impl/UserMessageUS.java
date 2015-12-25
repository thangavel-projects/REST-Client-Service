package com.sample.location.service.impl;

import java.util.Locale;

import com.sample.location.service.UserMessage;

/**
 * The <code>UserMessageUS</code> class represents to US Locale
 * to display the local description and it implements <code>UserMessage</code>
 * interface and implements <code>getCountryLocale</code> method to return US locale.
 * 
 * @author Thangavel Loganathan
 */

public class UserMessageUS implements UserMessage{

	/**
	 * The <code>getCountryLocale</code> method returns 
	 * US locale.
	 * 
	 * @return US Locale instance.
	 */

	public Locale getCountryLocale() {
		return new Locale("en", "US");
	}

}
