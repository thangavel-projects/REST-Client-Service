package com.sample.location.constants;

/**
 * The <code>Messages</code> represents to write locale description
 * from the message properties file and display user information in the console.
 * 
 * @author Thangavel Loganathan
 *
 */
public enum Messages {

	MESSAGE_ID_0("message_id_0"),
	MESSAGE_ID_1("message_id_1"),
	MESSAGE_ID_2("message_id_2"),
	MESSAGE_ID_3("message_id_3"),
	MESSAGE_ID_4("message_id_4"),
	MESSAGE_ID_5("message_id_5"),
	MESSAGE_ID_6("message_id_6"),
	MESSAGE_ID_7("message_id_7"),
	MESSAGE_ID_8("message_id_8"),
	MESSAGE_ID_9("message_id_9"),
	MESSAGE_ID_10("message_id_10");
	
	/**
	 * Key attribute uses to get the value from locale properties file.
	 */
	
	private String key;
	
	/**
	 * Construct instance of <code>Messages</code>
	 * @param key
	 */
	private Messages(String key) {
		this.key = key;
	}

	/**
	 * The <code>getKey</code> returns key from the enum to display message.
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
