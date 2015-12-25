package com.sample.location.service;

import java.util.Locale;

/**
 * The UserMessage interface provides the flexibility 
 * to MessageFactory to display any locale information.
 * 
 * @author Thangavel Loganathan
 */

public interface UserMessage {

	/**
	 * The <code>getCountryLocale</code> method provides country locale
	 * from the message factory.
	 * 
	 * @return Locale instance.
	 */
	public Locale getCountryLocale();
}
