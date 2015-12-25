package com.sample.location.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sample.location.constants.Messages;
import com.sample.location.dto.CityInfoDTO;
import com.sample.location.exception.CustomBusinessException;
import com.sample.location.factory.FileFactory;
import com.sample.location.helper.LocationServiceHelper;
import com.sample.location.service.FileGeneratable;
import com.sample.location.service.LocationService;
import com.sample.location.service.impl.EuropeanLocationService;

/**
 * This <code>InvokeRestService</code> class is the very first class when binary executed,
 * used to Invoke the Rest service and acts as a client.
 * <p>  
 */

public class InvokeRestService {
	
	/**
	 * Logger Instance
	 */
	
	private static final Logger _LOG = LoggerFactory.getLogger(InvokeRestService.class);
	
	/**
	 * The File Type attribute used to check the file type
	 */
	
	private static final String FILE_TYPE = "csv";
	
	/**
	 * The properties attribute used to read the property from config file
	 */
	
    private static Properties properties = null;
    
    /**
     * The <code>main</code> method would be invoked from the executable jar, this method gets
     * called very first time.
     * 
     * @param args
     * @throws CustomBusinessException
     * @throws IOException
     */
    
    public static void main (String[] args) throws CustomBusinessException, IOException {
    	
    	// The cityName attribute holds city name entered by end user.
    	StringBuilder cityName = new StringBuilder();
    	
    	// The fileType attribute holds file type from config file.
    	String fileType = null;

    	// The countrySet attribute holds list of country returned by location service
    	Set<String> countrySet = null;
    	
    	try {
			properties = LocationServiceHelper.loadConfigProperties();
			fileType = properties.getProperty("outFileFormat");
		} catch (IOException e) {
			_LOG.error("[ERROR] : Config file is not loaded properly..");
			throw new CustomBusinessException(e.getMessage());
		}
    	
    	try {
    		// Check if at least one argument entered by end user 
			if(args.length >= 1){
				for (String city : args) {
					cityName.append(city);
				}
			}else{
				_LOG.error("Please Pass Valid City Name to Location Service...");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// Set default language to US
			countrySet  = new HashSet<String>();
			countrySet.add("US");
			LocationServiceHelper.displayUserLogInfo(countrySet,Messages.MESSAGE_ID_1.getKey());
			throw new CustomBusinessException(e.getMessage());
		} 
		if (cityName != null || "".equals(cityName)) {
			
			// To avoid concurrency, synchronized block used.
			synchronized (InvokeRestService.class) {
				
				countrySet = LocationServiceHelper.getCountryList(cityName.toString());
				LocationService locationService = new EuropeanLocationService();
				
				// The cityInfoList attribute has list of  city information.
				List<CityInfoDTO> cityInfoList = locationService.getCityInformation(cityName.toString(),countrySet);
				LocationServiceHelper.displayUserLogInfo(countrySet,Messages.MESSAGE_ID_8.getKey());
				
				if( cityInfoList != null){
					try {
							if (cityInfoList.size() > 0 ) {
								if (FILE_TYPE.equalsIgnoreCase(fileType)) {
									
									// Get the File Format Instance from the Factory based on the file type. 
									FileGeneratable fileGenerator = FileFactory.getFileFormatInstance(fileType,countrySet);
									if (fileGenerator != null) {
										// Generate the Final CSV in local environment
										fileGenerator.generateFile(cityInfoList,cityName.toString());
									}
								} else {
									LocationServiceHelper.displayUserLogInfo(countrySet,Messages.MESSAGE_ID_9.getKey());
								}
							}
					} catch (Exception e) {
						LocationServiceHelper.displayUserLogInfo(countrySet,Messages.MESSAGE_ID_1.getKey());
						throw new CustomBusinessException(e.getMessage());
					}
				}
			}
		}
    }
}

