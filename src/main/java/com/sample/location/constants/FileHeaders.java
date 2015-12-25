package com.sample.location.constants;

/**
 * The <code>FileHeaders</code> enum represents header details to write CSV file
 * header and <code>getTittle</code> used to get the description.
 * 
 * @author Thangavel Loganathan
 */

public enum FileHeaders {
	
	CITY_ID("_id"),
	CITY_NAME("name"),
	CITY_TYPE("type"),
	CITY_LATITUTE("latitude"),
	CITY_LONGITUTE("longitude"),
	GEO_LOCATION("geo_position");
	
	/**
	 * Title attribute uses to get the header from the location service
	 */
	
	private String title;
	
	/**
     * Construct the Enum instance with <code>title</code> param. 
     * @param   title
     */
	
	private FileHeaders(String title){
		this.title = title;  
	}

	/**
	 * Return title from the Enum.
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	
}
