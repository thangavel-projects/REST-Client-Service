package com.sample.location.service;

import java.util.List;
import java.util.Set;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;
 
/**
 * The LocationService interface provides access to 
 * the other location service such as Asia or other.
 * 
 * @author Thangavel Loganathan
 * 
 */

public interface LocationService {

	/**
	 * The <code>getCityInformation</code> method provides  city
	 * information such name, type, and others.
	 * 
	 * @param cityName
	 * @param countrySet
	 * @return List<CityInfoDTO> instance
	 * @throws CustomBusinessException
	 * 
	 */
	
	public List<CityInfoDTO> getCityInformation(String cityName, Set<String> countrySet) throws CustomBusinessException ;

}
