package com.sample.location.service.impl;

import java.util.List;
import java.util.Set;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sample.location.constants.Messages;
import com.sample.location.constants.StatusCodes;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;
import com.sample.location.helper.LocationServiceHelper;
import com.sample.location.service.LocationService;

/**
 * The <code>EuropeanLocationService</code> class represents connect the 
 *  location service. This class implements <code>LocationService</code> interface.
 *  
 * <p> It get the JSON Objects and build the euro city
 * information from the service. If there is any error while connecting the location service, 
 * captures the error and displays to the end user</p> 
 * 
 * @author Thangavel Loganathan
 *
 */
public class EuropeanLocationService implements LocationService{

	/**
	 * Logger instance to display in the console
	 */
	
	private static final Logger _LOG = LoggerFactory.getLogger(EuropeanLocationService.class);
	
	/**
	 * The <code>getEuroCityInformation</code> method captures the euro city information
	 * from the service API and builds the DTO from JSON.
	 * 
	 * @return cityInfoList
	 */
	
	public List<CityInfoDTO> getCityInformation(String cityName, Set<String> countrySet) throws CustomBusinessException {
		
		// The cityInfo attribute reads the entity from the location service.
		String cityInfoData = null;

		// The cityInfoList has list of  city information
		List<CityInfoDTO> cityInfoList = null;

		// Invoke the location service.
		Response response = LocationServiceHelper.invokeLocationService(cityName);
		
		// If the connection is success
		if (response.getStatus() == StatusCodes.SUCCESS.getStatusCode()) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_9.getKey());
			cityInfoData = response.readEntity(String.class);
			JSONArray cityInfoJsonArrayObject;
			try {
				cityInfoJsonArrayObject = new JSONArray(cityInfoData);
				cityInfoList = LocationServiceHelper.buildCityInfoList(cityInfoJsonArrayObject,countrySet);
				LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_4.getKey());
			} catch (JSONException e) {
				LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
				_LOG.error("[Error] : Unable to parse the JSON Array!");
				throw new CustomBusinessException(e.getMessage());
			}
		} else if (response.getStatus()  == StatusCodes.PROXY_AUTHENTICATION_REQUIRED.getStatusCode()) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
			_LOG.error(StatusCodes.PROXY_AUTHENTICATION_REQUIRED.getStatusDescription());
			throw new CustomBusinessException( "Failed to Connect Rest Service : Proxy Authentication Required "+response.getStatus() );
		} else if (response.getStatus()  == StatusCodes.INTERNAL_SERVER_ERROR.getStatusCode() ) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
			_LOG.error(StatusCodes.INTERNAL_SERVER_ERROR.getStatusDescription());
			throw new CustomBusinessException( "Failed to Connect Rest Service : Internal Server Error "+response.getStatus());
		} else if (response.getStatus()  == StatusCodes.FORBIDDEN_ERROR.getStatusCode()) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
			_LOG.error(StatusCodes.FORBIDDEN_ERROR.getStatusDescription());
			throw new CustomBusinessException( "Failed to Connect Rest Service : Forbidden Error "+response.getStatus());
		} else if (response.getStatus()  == StatusCodes.REQUEST_TIME_OUT.getStatusCode()) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
			_LOG.error(StatusCodes.REQUEST_TIME_OUT.getStatusDescription());
			throw new CustomBusinessException( "Failed to Connect Rest Service : Request Time out Error "+response.getStatus());
		} else if (response.getStatus()  == StatusCodes.UNAUTHORISED_ERROR.getStatusCode()) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
			_LOG.error(StatusCodes.UNAUTHORISED_ERROR.getStatusDescription());
			throw new CustomBusinessException( "Failed to Connect Rest Service : Unauthorised User Error "+response.getStatus());
		} else if (response.getStatus()  == StatusCodes.BAD_REQUEST.getStatusCode()) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
			_LOG.error(StatusCodes.BAD_REQUEST.getStatusDescription());
			throw new CustomBusinessException( "Failed to Connect Rest Service : Bad Request Found "+response.getStatus());
		} else if (response.getStatus()  == StatusCodes.PAGE_NOT_FOUND.getStatusCode()) {
			LocationServiceHelper.displayUserLogInfo(countrySet, Messages.MESSAGE_ID_5.getKey());
			_LOG.error(StatusCodes.PAGE_NOT_FOUND.getStatusDescription());
			throw new CustomBusinessException( "Failed to Connect Rest Service : Page Not Found "+response.getStatus());
		} 
		return cityInfoList;
	}
	
}
