package com.sample.location.factory;

import junit.framework.TestCase;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Assert;
import com.sample.location.service.FileGeneratable;
import com.sample.location.service.impl.CSVFileGenerator;

/**
 * The <code>FileFactoryTest</code> class captures all the 
 * JUnit test case of <code>FileFactory</code>.
 * 
 * @author Thangavel Loganathan
 * 
 */

public class FileFactoryTest extends TestCase{
	
	private CSVFileGenerator csvFileGenerator = null;
	private FileGeneratable fileFormatInstance  = null;
	
	public FileFactoryTest(String testCaseName){
		super(testCaseName);
	}
	
	/**
	 *  The <code>setUp</code> method initialize the necessary objects.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		csvFileGenerator = new CSVFileGenerator();
	}
	
	/**
	 * The <code>testGetFileFormatInstance</code> method covers
	 * all the scenario test cases.
	 * 
	 */
	public void testGetFileFormatInstance(){
		
		fileFormatInstance = FileFactory.getFileFormatInstance("CSV", null);
		Assert.assertNotNull(fileFormatInstance);
		Assert.assertThat(csvFileGenerator,instanceOf(fileFormatInstance.getClass()));
		
		fileFormatInstance = FileFactory.getFileFormatInstance("PDF", null);
		Assert.assertNotEquals(csvFileGenerator, fileFormatInstance);
		
		fileFormatInstance = FileFactory.getFileFormatInstance("", null);
		Assert.assertNull(fileFormatInstance);
		
		fileFormatInstance = FileFactory.getFileFormatInstance(null, null);
		Assert.assertNull(fileFormatInstance);
	}
}
