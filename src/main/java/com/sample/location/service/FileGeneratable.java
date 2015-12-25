package com.sample.location.service;

import java.util.List;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;

/**
 * The FileGeneratable interface provides the flexibility for 
 * file generator factory and represents middle ware.
 * 
 * @author Thangavel Loganathan
 *
 */
public interface FileGeneratable {
	

	/**
	 * The <code>generateFile</code> method used to generate a generic 
	 * file type report(s).
	 * 
	 * @param cityInfoDTO
	 * @param cityName
	 * @throws CustomBusinessException
	 */
	public boolean generateFile(List<CityInfoDTO>  cityInfoDTO, String cityName) throws CustomBusinessException ;
}
