package com.sample.location.factory;

import org.junit.Assert;
import static org.hamcrest.CoreMatchers.instanceOf;
import junit.framework.TestCase;
import com.sample.location.service.UserMessage;
import com.sample.location.service.impl.UserMessageDE;
import com.sample.location.service.impl.UserMessageFR;
import com.sample.location.service.impl.UserMessageIT;
import com.sample.location.service.impl.UserMessageUS;

/**
 * The <code>UserMessageFactoryTest</code> class captures all the 
 * JUnit test case of <code>UserMessageFactory</code>.
 * 
 * @author Thangavel Loganathan
 * 
 */

public class UserMessageFactoryTest extends TestCase{
	
	private UserMessageUS userMessageUS = null;
	private UserMessageDE userMessageDE = null;
	private UserMessageIT userMessageIT = null;
	private UserMessageFR userMessageFR = null;
	private UserMessage countryLocaleByCode = null;
	
	public UserMessageFactoryTest(String testCaseName){
		super(testCaseName);
	}
	
	/**
	 *  The <code>setUp</code> method initialize the necessary objects.
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		userMessageUS = new UserMessageUS();
		userMessageDE = new UserMessageDE();
		userMessageIT = new UserMessageIT();
		userMessageFR = new UserMessageFR();
	}

	/**
	 * The <code>testGetCountryLocaleByCode</code> method covers 
	 * all the scenario JUnit Test cases.
	 */
	public void testGetCountryLocaleByCode(){
		
		countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode("US");
		Assert.assertNotNull(countryLocaleByCode);
		Assert.assertThat(userMessageUS,instanceOf(countryLocaleByCode.getClass()));
		
		countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode("DE");
		Assert.assertNotNull(countryLocaleByCode);
		Assert.assertThat(userMessageDE,instanceOf(countryLocaleByCode.getClass()));
		
		countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode("IT");
		Assert.assertNotNull(countryLocaleByCode);
		Assert.assertThat(userMessageIT,instanceOf(countryLocaleByCode.getClass()));
		
		countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode("FR");
		Assert.assertNotNull(countryLocaleByCode);
		Assert.assertThat(userMessageFR,instanceOf(countryLocaleByCode.getClass()));
		
		countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode("");
		Assert.assertNull(countryLocaleByCode);
		
		countryLocaleByCode = UserMessageFactory.getCountryLocaleByCode(null);
		Assert.assertNull(countryLocaleByCode);
		
	}
}
