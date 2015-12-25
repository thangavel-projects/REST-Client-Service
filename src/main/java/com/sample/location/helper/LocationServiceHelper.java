package com.sample.location.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sample.location.constants.FileHeaders;
import com.sample.location.constants.Messages;
import com.sample.location.constants.StatusCodes;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;
import com.sample.location.factory.UserMessageFactory;
import com.sample.location.service.UserMessage;
import com.sample.location.service.impl.CSVFileGenerator;

/**
 * The <code>LocationServiceHelper</code> class represents as utility class
 * where in load property, building DTO functionalities carried out with static 
 * methods.
 * 
 * @author Thangavel Loganathan
 *
 */

public class LocationServiceHelper {
	
	/**
	 * Logger Instance
	 */
	private static final Logger _LOG = LoggerFactory.getLogger(CSVFileGenerator.class);
	
	/**
	 * The LOCATION_SERVICE_URL attribute has Location Service API URL
	 */
	private final static String LOCATION_SERVICE_URL = "<URL of Rest Server>";
	
	/**
	 * The NO_RESPONSE_DATA attribute used if the JSON Object empty.
	 */
	private final static int EMPTY_JSON_OBJECT = 2;
	
	
	/**
	 * The <code>loadConfigProperties</code> method loads the config
	 * file and gets the file format value.
	 * 
	 * @return properties
	 * @throws IOException
	 * @throws CustomBusinessException
	 */
	
	public static Properties loadConfigProperties() throws IOException, CustomBusinessException {
		InputStream stream = null;
		Properties properties = null;
		try {
			stream  = LocationServiceHelper.class.getClassLoader().getResourceAsStream("config.properties");
			properties = new Properties();
			if(stream != null){
				properties.load(stream);
			}
		} catch (FileNotFoundException e) {
			_LOG.error("[ERROR] : Config Property File Not Found.");
			throw new CustomBusinessException(e.getMessage());
		}finally{
			if(stream != null){
				stream.close();
			}
		}
		return properties;
	}

	/**
	 * The <code>buildCityInfoList</code> methods builds the DTO from the 
	 * JSON object and return it.
	 * 
	 * <p>If any exception occurs, catch it and throws to business exception</P>
	 * 
	 * @param cityInfoJsonArray
	 * @param countrySet
	 * @return List Instance
	 * @throws CustomBusinessException
	 */
	
	public static List<CityInfoDTO> buildCityInfoList(JSONArray cityInfoJsonArray,Set<String> countrySet) throws CustomBusinessException{
		
		LocationServiceHelper.displayUserLogInfo(countrySet,Messages.MESSAGE_ID_7.getKey());
		List<CityInfoDTO> cityInfoList = new ArrayList<CityInfoDTO>();
		
		for (int i = 0 ; i < cityInfoJsonArray.length(); i++) {
			try {
				CityInfoDTO cityInfoDTO = new CityInfoDTO();
				JSONObject cityInfo = cityInfoJsonArray.getJSONObject(i);
			    cityInfoDTO.setId(cityInfo.getString(FileHeaders.CITY_ID.getTitle()));
			    cityInfoDTO.setName(cityInfo.getString(FileHeaders.CITY_NAME.getTitle()));
			    cityInfoDTO.setType(cityInfo.getString(FileHeaders.CITY_TYPE.getTitle()));
			    JSONObject cityGeoPositionJson = (JSONObject) cityInfo.get(FileHeaders.GEO_LOCATION.getTitle());	
			    cityInfoDTO.setLatitude(cityGeoPositionJson.getString(FileHeaders.CITY_LATITUTE.getTitle()));
			    cityInfoDTO.setLongitude(cityGeoPositionJson.getString(FileHeaders.CITY_LONGITUTE.getTitle()));
			    cityInfoList.add(cityInfoDTO);
			} catch (JSONException e) {
				_LOG.error("[Error] : Exception occured while processing JSON Object, Please verify the JSON Object format!");
				throw new CustomBusinessException(e.getMessage());
			}
		}
		return cityInfoList;
	}
	
	/**
	 * The <code>invokeLocationService</code> method invokes the  Location
	 * service and returns the response.
	 * 
	 * @param cityName
	 * @return locationResponse
	 * @throws CustomBusinessException
	 */
	
	public static Response invokeLocationService(String cityName) throws CustomBusinessException{
		
		Response locationResponse = null;
		Set<String> countryCodeDefault = null;
		try {
			ResteasyClient client = new ResteasyClientBuilder().build();
			ResteasyWebTarget target = client.target(LOCATION_SERVICE_URL+cityName);
			locationResponse =  target.request().get();
		} catch (ProcessingException e) {
			countryCodeDefault = new HashSet<String>();
			countryCodeDefault.add("US");
			displayUserLogInfo(countryCodeDefault,Messages.MESSAGE_ID_0.getKey());
			throw new CustomBusinessException(e.getMessage());
		} catch (Exception e) {
			countryCodeDefault = new HashSet<String>();
			countryCodeDefault.add("US");
			displayUserLogInfo(countryCodeDefault,Messages.MESSAGE_ID_0.getKey());
			throw new CustomBusinessException(e.getMessage());
		}
		return locationResponse;
		
	}
	
	/**
	 * The <code>getCountryList</code> method invokes the location
	 * service API and returns list of country code.
	 * 
	 * @param cityName
	 * @return countrySet
	 * @throws CustomBusinessException
	 */
	public static Set<String> getCountryList(String cityName) throws CustomBusinessException{
		
		Set<String> countrySet = new HashSet<String>();
		Response response = invokeLocationService(cityName);
		
		if (response.getStatus() == StatusCodes.SUCCESS.getStatusCode()) {
			_LOG.info("Server Status :"+response.getStatus()+" Server Connection Established...");
			String cityInfoData = response.readEntity(String.class);
			
			if(cityInfoData.length()  == EMPTY_JSON_OBJECT){
				_LOG.info("Please Pass Valid City Name to  Location Service...");
				throw new CustomBusinessException();
			}
			try {
				JSONArray cityInfoJsonObject = new JSONArray(cityInfoData);
				for (int i = 0 ; i < cityInfoJsonObject.length(); i++) {
						try {
							JSONObject cityInfo = cityInfoJsonObject.getJSONObject(i);
							countrySet.add(cityInfo.getString("countryCode"));
						} catch (JSONException e) {
							_LOG.error("[Error] : Exception occured while processing JSON Object, Please verify the JSON Object format!");
							throw new CustomBusinessException(e.getMessage());
						}
					} 
				}
				catch (JSONException e) {
				_LOG.error("[Error] : Unable to parse the JSON Array!");
				throw new CustomBusinessException(e.getMessage());
			}
		}
		return countrySet;
	}
	
	/**
	 * The <code>displayUserLogInfo</code> method used to display locale specific
	 * message to the end user in the console.
	 * 
	 * @param countrySet
	 * @param messageKey
	 */
	
	public static void displayUserLogInfo(Set<String> countrySet, String messageKey) {
		
		if(countrySet != null){
			for (String countryCode : countrySet) {
				if("US".equalsIgnoreCase(countryCode) || "DE".equalsIgnoreCase(countryCode) ||
						"FR".equalsIgnoreCase(countryCode) || "IT".equalsIgnoreCase(countryCode)){
					UserMessage countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode(countryCode);
					ResourceBundle labels = ResourceBundle.getBundle("message",countryLocaleByCode.getCountryLocale());
					_LOG.info(labels.getString(messageKey));
				} else {
					UserMessage countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode("US");
					ResourceBundle labels = ResourceBundle.getBundle("message",countryLocaleByCode.getCountryLocale());
					_LOG.info(labels.getString(messageKey));
				}
			}
		}
		
	}
	
	/**
	 * The <code>displayUserLogInfo</code> method used to display final file location in
	 * the all language to the end user.
	 * 
	 * @param countrySet
	 * @param messageKey
	 */
	
	public static void displayUserLogInfo(String csvFileLocation) {
		_LOG.info("### The CSV File Path is Generated in the location : " +
				"|| Das CSV-Dateipfad wird in der generierten Lage : " +
				"|| Il percorso del file CSV si genera nel Lage posizione : " +
				"|| Le Chemin du fichier CSV est g�n�r� dans le emplacement : "+csvFileLocation+" ###");
	}
	
}
