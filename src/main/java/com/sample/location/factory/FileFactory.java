package com.sample.location.factory;

import java.util.Set;
import com.sample.location.constants.Messages;
import com.sample.location.helper.LocationServiceHelper;
import com.sample.location.service.FileGeneratable;
import com.sample.location.service.impl.CSVFileGenerator;

/**
 * The <code>FileFactory</code> class represents the Factory patterns
 * to return the CSV File Generator instance for writing files.
 * 
 * @author Thangavel Loganathan
 * 
 */

public class FileFactory {
	
	/**
	 * The static <code>getFileFormatInstance</code> method used to return the 
	 * instance of the file type and accepts country set to display the 
	 * locale message description in the console.
	 * 
	 * @param fileType
	 * @param countrySet
	 * @return fileGenerator
	 * 
	 */
	public static FileGeneratable getFileFormatInstance(String fileType, Set<String> countrySet){
		
		FileGeneratable fileGenerator = null;
		
		if("CSV".equalsIgnoreCase(fileType)){
			fileGenerator =  new CSVFileGenerator();
			if (countrySet != null){
				LocationServiceHelper.displayUserLogInfo(countrySet,Messages.MESSAGE_ID_6.getKey());
			}
			return fileGenerator;
		}
		return fileGenerator;
	}

}
