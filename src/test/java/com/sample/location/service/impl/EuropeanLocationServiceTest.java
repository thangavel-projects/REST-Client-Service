package com.sample.location.service.impl;

import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Assert;
import org.skyscreamer.jsonassert.JSONAssert;
import com.sample.location.exception.CustomBusinessException;
import com.sample.location.helper.LocationServiceHelper;
import junit.framework.TestCase;

/**
 * The <code>EuropeanLocationServiceTest</code> class used to capture all
 * the JUnit test cases from <code>EuropeanLocationService</code> class. 
 * 
 * @author Thangavel Loganathan
 *
 */

public class EuropeanLocationServiceTest extends TestCase{
	
	private Response response = null;
	private String expectedJSON =  null;
	
	public EuropeanLocationServiceTest(String testCaseName){
		super(testCaseName);
	}

	/**
	 *  The <code>setUp</code> method initialize the necessary objects.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		response = LocationServiceHelper.invokeLocationService("Swiss");
		expectedJSON = "[{\"_id\":411337,\"key\":null,\"name\":\"Swissvale\",\"fullName\":\"Swissvale, USA\"," +
				"\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"USA\",\"geo_position\":{\"latitude\":40.42368," +
				"\"longitude\":-79.88283},\"locationId\":43573,\"inEurope\":false,\"countryCode\":\"US\",\"coreCountry\":false,\"distance\":null}]";
	}
	
	/**
	 * The <code>testGetEuroCityInformation()</code> method captures all the business
	 * logic test scenarios.
	 * @throws CustomBusinessException
	 * @throws JSONException
	 */
	public void testGetEuroCityInformation() throws CustomBusinessException, JSONException{
		
		Assert.assertEquals(200, response.getStatus());
		response = LocationServiceHelper.invokeLocationService("");
		Assert.assertEquals(500, response.getStatus());
		
		response = LocationServiceHelper.invokeLocationService(null);
		Assert.assertEquals(200, response.getStatus());
		response = LocationServiceHelper.invokeLocationService("Swiss");
		
		String euroCityInfo = response.readEntity(String.class);
		JSONArray euroCityInfoJsonArray = new JSONArray(euroCityInfo);
		JSONAssert.assertEquals(expectedJSON,euroCityInfoJsonArray,false);
	}
	
}
