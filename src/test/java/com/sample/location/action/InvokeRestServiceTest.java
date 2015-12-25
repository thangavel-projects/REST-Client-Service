package com.sample.location.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;
import com.sample.location.factory.FileFactory;
import com.sample.location.helper.LocationServiceHelper;
import com.sample.location.service.FileGeneratable;
import com.sample.location.service.LocationService;
import com.sample.location.service.impl.EuropeanLocationService;

/**
 * The <code>InvokeRestServiceTest</code> represents captures 
 * all the JUnit test scenarios from the <code>InvokeLocationService</code>.
 * 
 * @author Thangavel Loganathan
 *
 */
public class InvokeRestServiceTest extends TestCase{

	private Set<String> countrySet = null;
	private LocationService locationService = null;
	private CityInfoDTO  cityInfoDTO = null;
	private List<CityInfoDTO> cityList = null;
	private FileGeneratable fileGenerator = null;
	
	public InvokeRestServiceTest(String name) {
		super(name);
	}

	/**
	 *  The <code>setUp</code> method initialize the necessary objects.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		locationService = new EuropeanLocationService();
		countrySet = new HashSet<String>();
		countrySet.add("DE");
		countrySet.add("IT");
		
		cityInfoDTO = new CityInfoDTO();
		cityInfoDTO.setId("411337");
		cityInfoDTO.setName("Swissvale");
		cityInfoDTO.setType("location");
		cityInfoDTO.setLatitude("40.42368");
		cityInfoDTO.setLongitude("-79.88283");
		
		cityList = new ArrayList<CityInfoDTO>();
		cityList.add(cityInfoDTO);
		
		fileGenerator  = FileFactory.getFileFormatInstance("csv",countrySet);
	}
	
	/**
	 * The <code>testMain()</code> method captures all the business
	 * logic test scenarios.
	 * 
	 * @throws IOException
	 * @throws CustomBusinessException
	 */
	@Test
	public void testMain() throws IOException, CustomBusinessException{
		String [] cityName = {"Berlin"};
		Assert.assertArrayEquals(cityName, new String[]{"Berlin"});
		cityName = null;
		Assert.assertNull(cityName);
		Assert.assertNotSame("", cityName);
		Assert.assertNotNull(getClass().getResourceAsStream("/config.properties"));
		
		Properties properties = LocationServiceHelper.loadConfigProperties();
		String fileType = properties.getProperty("outFileFormat");
		Assert.assertNotNull(fileType);
		Assert.assertEquals("csv", fileType);
		Assert.assertNotSame("", fileType);
		
		Set<String> countryList = LocationServiceHelper.getCountryList("Berlin");
		Assert.assertNotNull(countryList);
		Assert.assertEquals(countrySet, countryList);
		
		List<CityInfoDTO> cityListInfoDTO = locationService.getCityInformation("Swiss", null);
		
		Assert.assertEquals(cityList.size(), cityListInfoDTO.size());
		Assert.assertArrayEquals(cityList.toArray(), cityListInfoDTO.toArray());
		Assert.assertEquals(true, fileGenerator.generateFile(cityListInfoDTO, "Swiss"));
		
	}
}

