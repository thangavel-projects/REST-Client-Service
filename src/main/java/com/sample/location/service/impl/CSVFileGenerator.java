package com.sample.location.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sample.location.constants.FileHeaders;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;
import com.sample.location.helper.LocationServiceHelper;
import com.sample.location.service.FileGeneratable;

/**
 * The <code>CSVFileGenerator</code> class used to generate the CSV
 * file and store it in local environment. It implements <code>FileGeneratable</code>
 * interface and has properties of that interface.
 * 
 * @author Thangavel Loganathan
 *
 */

public class CSVFileGenerator implements FileGeneratable{

	/**
	 * Logger instance to display in the console
	 */
	
	private static final Logger _LOG = LoggerFactory.getLogger(CSVFileGenerator.class);
	
	/**
	 * The NEW_LINE_SEPARATOR attribute write the CSV in new line for every records.
	 */
	
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	/**
	 * The FILE_COLUMN_HEADERS attribute to display the CSV file headers.
	 */
	
	private static final Object [] FILE_COLUMN_HEADERS = { 	FileHeaders.CITY_ID.getTitle(),
															FileHeaders.CITY_NAME.getTitle(),
															FileHeaders.CITY_TYPE.getTitle(),
															FileHeaders.CITY_LATITUTE.getTitle(),
															FileHeaders.CITY_LONGITUTE.getTitle() 
														 } ;
	
	/**
	 * The <code>generateFile</code> method facilitate to write CSV file 
	 * in the local environment, which accepts <code>List</code> and <code>String</code>.
	 * 
	 * @throws CustomBusinessException
	 * @throws IOException
	 * @throws Exception
	 * 
	 */
	
	public boolean generateFile(List<CityInfoDTO>  cityInfoDto, String cityName) throws CustomBusinessException {
		
		// Instantiate fileWrite attribute
		FileWriter fileWriter = null;
		
		// Instantiate csvFilePrinter attribute
		CSVPrinter csvFilePrinter = null;
		
		// Check whether file generated successfully.
		boolean isFileGenerated = false;
		
		// Get the CSV Format
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
	
        if (cityInfoDto != null) {
	        if (cityName != null) {
	        	if (!cityName.isEmpty()) {
			        try {
				        String csvFileLocation = System.getProperty("user.home")+File.separator+cityName+".csv";
						fileWriter = new FileWriter(csvFileLocation);
				        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
				        csvFilePrinter.printRecord(FILE_COLUMN_HEADERS);
				        for (CityInfoDTO cityInfo : cityInfoDto) {
				        	List<String> cityList = new ArrayList<String>();
				        	cityList.add(cityInfo.getId());
				        	cityList.add(cityInfo.getName());
				        	cityList.add(cityInfo.getType());
				        	cityList.add(cityInfo.getLatitude());
				        	cityList.add(cityInfo.getLongitude());
				        	csvFilePrinter.printRecord(cityList);
						}
				        isFileGenerated = true;
				        LocationServiceHelper.displayUserLogInfo(csvFileLocation);
					} catch (Exception e) {
						_LOG.error("[Error] : Exception occured during file writing, Please check the log for more information");
						throw new CustomBusinessException(e.getMessage());
					} finally {
						try {
							if(fileWriter != null){
								fileWriter.flush();
								fileWriter.close();
							} 
							if(csvFilePrinter != null){
								csvFilePrinter.close();
							}
						} catch (IOException e) {
							_LOG.error("[Error] : Error while flushing/closing fileWriter/csvPrinter.");
							throw new CustomBusinessException(e.getMessage());
						}
					}
	        	}
	        }
        }
		return isFileGenerated;
	}
}
