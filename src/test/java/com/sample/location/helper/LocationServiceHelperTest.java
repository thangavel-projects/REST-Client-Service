package com.sample.location.helper;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;
import com.sample.location.factory.UserMessageFactory;
import com.sample.location.service.UserMessage;

import junit.framework.TestCase;

/**
 * The <code>LocationServiceHelperTest</code> class used to test 
 * all the scenarios from <code>LocationServiceHelper</code> class.
 * 
 * @author Thangavel Loganathan
 *
 */

public class LocationServiceHelperTest extends TestCase {
	
	private Set<String> countrySetExpected = null;
	private Set<String> countrySet = null;
	private Response response = null;
	private CityInfoDTO  cityInfoDTO = null;
	private List<CityInfoDTO> cityList = null;
	private Response expectedResponse = null;
	private String cityInfo = null;
	private JSONArray cityInfoJsonArray = null;
	private List<CityInfoDTO> buildCityInfoList = null;
	private String userInfoMessage = null;
	private UserMessage countryLocaleByCode = null;
	private ResourceBundle labels = null;
	
	private final String LOCATION_SERVICE_URL = "<URL of Rest Service>";
	
	
	public LocationServiceHelperTest(String testCaseName){
		super(testCaseName);
	}
	
	/**
	 *  The <code>setUp</code> method initialize the necessary objects.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		countrySetExpected = new HashSet<String>();
		countrySetExpected.add("DE");
		countrySetExpected.add("IT");
		
		cityInfoDTO = new CityInfoDTO();
		cityInfoDTO.setId("411337");
		cityInfoDTO.setName("Swissvale");
		cityInfoDTO.setType("location");
		cityInfoDTO.setLatitude("40.42368");
		cityInfoDTO.setLongitude("-79.88283");
		
		cityList = new ArrayList<CityInfoDTO>();
		cityList.add(cityInfoDTO);
		
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(LOCATION_SERVICE_URL+"Swiss");
		expectedResponse =  target.request().get();
		
		countrySet = new HashSet<String>();
		
		String cityInfoData = expectedResponse.readEntity(String.class);
		JSONArray cityInfoJsonArray = new JSONArray(cityInfoData);
		JSONObject cityInfo = cityInfoJsonArray.getJSONObject(0);
		countrySet.add(cityInfo.getString("countryCode"));
		
		userInfoMessage = "Completed Building City Info List.";
		
	}
	
	/**
	 * The <code>testLoadConfigProperties</code> method used to test the config
	 * file.
	 */
	
	public void testLoadConfigProperties() {
		Assert.assertNotNull(getClass().getResourceAsStream("/config.properties"));
		Assert.assertNull(getClass().getResourceAsStream("config"));
	}

	/**
	 * The <code>testBuildCityInfoList</code> methods used to test the 
	 * City Info DTO.
	 * 
	 * @throws CustomBusinessException 
	 * @throws JSONException 
	 * 
	 */
	
	public void testBuildCityInfoList() throws CustomBusinessException, JSONException {
		
		response = LocationServiceHelper.invokeLocationService("Paris");
		cityInfo = response.readEntity(String.class);
		cityInfoJsonArray = new JSONArray(cityInfo);
		buildCityInfoList = LocationServiceHelper.buildCityInfoList(cityInfoJsonArray, countrySetExpected);
		
		Assert.assertNotEquals(cityList.size(), buildCityInfoList.size());
		
		Assert.assertNotEquals(cityList.get(0).getId() , buildCityInfoList.get(0).getId());
		Assert.assertNotEquals(cityList.get(0).getName() , buildCityInfoList.get(0).getName());
		Assert.assertNotEquals(cityList.get(0).getLatitude() , buildCityInfoList.get(0).getLatitude());
		Assert.assertNotEquals(cityList.get(0).getLongitude() , buildCityInfoList.get(0).getLongitude());
		
		response = LocationServiceHelper.invokeLocationService("Swiss");
		cityInfo = response.readEntity(String.class);
		cityInfoJsonArray = new JSONArray(cityInfo);
		buildCityInfoList = LocationServiceHelper.buildCityInfoList(cityInfoJsonArray, countrySetExpected);
		
		Assert.assertEquals(cityList.size(), buildCityInfoList.size());
		
		Assert.assertEquals(cityList.get(0).getId() , buildCityInfoList.get(0).getId());
		Assert.assertEquals(cityList.get(0).getName() , buildCityInfoList.get(0).getName());
		Assert.assertEquals(cityList.get(0).getLatitude() , buildCityInfoList.get(0).getLatitude());
		Assert.assertEquals(cityList.get(0).getLongitude() , buildCityInfoList.get(0).getLongitude());
		
		
	}
	
	/**
	 * The <code>testInvokeLocationService</code> method used to capture
	 * the response type as expected.
	 * 
	 */
	
	public void testInvokeLocationService() {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(LOCATION_SERVICE_URL+"Berlin");
		Response locationResponse =  target.request().get();
		
		Assert.assertNotNull(locationResponse);
		Assert.assertThat(expectedResponse,instanceOf(locationResponse.getClass()));
		
	}
	
	/**
	 * The <code>testCountryList</code> method represents the country
	 * code test cases.
	 * 
	 * @throws JSONException 
	 * @throws CustomBusinessException 
	 */

	public void testCountryList() throws JSONException, CustomBusinessException {
		countrySetExpected = new HashSet<String>();
		countrySetExpected.add("US");
		Assert.assertArrayEquals(countrySetExpected.toArray() , countrySet.toArray());
		countrySetExpected.add("DE");
		Assert.assertNotEquals(countrySetExpected.toArray() , countrySet.toArray());
	}
	
	/**
	 * The <code>testDisplayUserLogInfo</code> method used to capture the
	 * locale specific test cases.
	 * 
	 */
	
	public void testDisplayUserLogInfo() {
		countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode("US");
		labels = ResourceBundle.getBundle("message",countryLocaleByCode.getCountryLocale());
		
		Assert.assertEquals(userInfoMessage, labels.getString("message_id_8"));
		Assert.assertNotEquals(userInfoMessage, labels.getString("message_id_9"));
		Assert.assertNotEquals(userInfoMessage, "");
		
	}
	
}
