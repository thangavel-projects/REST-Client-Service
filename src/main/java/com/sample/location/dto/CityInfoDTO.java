package com.sample.location.dto;

/**
 * The <code>CityInfoDTO</code> class represents to carry CSV file 
 * information between the layers.
 * 
 * @author Thangavel Loganathan
 *
 */

public class CityInfoDTO {

	/**
	 * Id attribute has the city id.
	 */
	
	private String id;
	
	/**
	 * The name attribute has the name of the  city.
	 */
	
	private String name;
	
	/**
	 * The type attribute has the type of the  city.
	 */
	
	private String type;
	
	/**
	 * The latitude attribute has the latitude of the  particular city.
	 */
	
	private String latitude;
	
	/**
	 * The longitude attribute has the longitude of the  particular city.
	 */
	
	private String longitude;

	/**
	 * The <code>getId</code> method returns id of the   
	 * city from the location service API. 
	 * 
	 * @return the id
	 */
	
	public String getId() {
		return id;
	}
	
	/**
	 * The <code>getId</code> method set id of the 
	 * city to DTO
	 * 
	 * @param id
	 */
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * The <code>getName</code> method returns name of the   
	 * city from the location service API. 
	 * 
	 * @return the name
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * The <code>setName</code> method set name of the 
	 * city to DTO
	 * 
	 * @param name the name to set
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * The <code>getType</code> method returns type of the   
	 * city from the location service API.
	 * @return the type
	 */
	
	public String getType() {
		return type;
	}
	
	/**
	 * The <code>setType</code> method set type of the 
	 * city to DTO
	 * 
	 * @param type the type to set
	 */
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * The <code>getLatitude</code> method returns latitude of the   
	 * city from the location service API.
	 * 
	 * @return the latitude
	 */
	
	public String getLatitude() {
		return latitude;
	}
	
	/**
	 * The <code>setLatitude</code> method set latitude of the 
	 * city to DTO
	 * 
	 * @param latitude the latitude to set
	 */
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * The <code>getLongitude</code> method returns longitude of the   
	 * city from the location service API.
	 * 
	 * @return the longitude
	 */
	
	public String getLongitude() {
		return longitude;
	}
	
	/**
	 * The <code>setLongitude</code> method set longitude of the 
	 * city to DTO
	 * 
	 * @param longitude the longitude to set
	 */
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * Override <code>hascode</code> method for all the attribute. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/**
	 * Override <code>equals</code> method for all the attribute. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CityInfoDTO other = (CityInfoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
