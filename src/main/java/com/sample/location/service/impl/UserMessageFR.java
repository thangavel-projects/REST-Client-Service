package com.sample.location.service.impl;

import java.util.Locale;

import com.sample.location.service.UserMessage;

/**
 * The <code>UserMessageFR</code> class represents to France Locale
 * to display the local description and it implements <code>UserMessage</code>
 * interface and implements <code>getCountryLocale</code> method to return France locale.
 * 
 * @author Thangavel Loganathan
 */

public class UserMessageFR implements UserMessage{


	/**
	 * The <code>getCountryLocale</code> method returns 
	 * France locale.
	 * 
	 * @return France Locale instance.
	 */
	
	public Locale getCountryLocale() {
		return new Locale("fr", "FR");
	}

}
