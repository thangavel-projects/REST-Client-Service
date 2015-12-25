package com.sample.location.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import junit.framework.TestCase;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;

/**
 * The <code>CSVFileGeneratorTest</code> class used to capture the CSV
 * file generation JUnit Test case from <code>CSVFileGenerator</code> class.
 * 
 * @author Thangavel Loganathan
 *
 */

public class CSVFileGeneratorTest extends TestCase {

	private CSVFileGenerator csvFileGenerator = null;
	private CityInfoDTO  cityInfoDTO = null;
	private List<CityInfoDTO> cityList = null;
	
	public CSVFileGeneratorTest(String testCaseName){
		super(testCaseName);
	}
	
	/**
	 *  The <code>setUp</code> method initialize the necessary objects.
	 */
	@Override
	protected void setUp() throws Exception {
		csvFileGenerator = new CSVFileGenerator();
		cityInfoDTO = new CityInfoDTO();
		cityInfoDTO.setId("411337");
		cityInfoDTO.setName("Swissvale");
		cityInfoDTO.setType("location");
		cityInfoDTO.setLatitude("40.42368");
		cityInfoDTO.setLongitude("-79.88283");
		
		cityList = new ArrayList<CityInfoDTO>();
		cityList.add(cityInfoDTO);
		
	}
	/**
	 * The <code>testGenerateFile</code> method captures whether 
	 * CSV generated successfully.
	 * 
	 * @throws CustomBusinessException
	 */
	public void testGenerateFile() throws CustomBusinessException {
		Assert.assertEquals(true, csvFileGenerator.generateFile(cityList, "Austria"));
		Assert.assertEquals(false, csvFileGenerator.generateFile(null, "Austria"));
		Assert.assertEquals(false, csvFileGenerator.generateFile(null, ""));
		Assert.assertEquals(false, csvFileGenerator.generateFile(null, null));
	}
}
