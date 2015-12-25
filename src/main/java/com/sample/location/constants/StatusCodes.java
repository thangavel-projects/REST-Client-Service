package com.sample.location.constants;

/**
 * The <code>StatusCodes</code> represents to display the response 
 * from Rest Service API and display user about the error description to user if any.
 * 
 * @author Thangavel Loganathan
 *
 */


public enum StatusCodes {

	SUCCESS(200,"Success"),
	BAD_REQUEST(400, "[Error] : Error Code : 400 Unable to connect the server, Bad Request Found while trying to connect the server"),
	UNAUTHORISED_ERROR(401,"[Error] : Error Code : 401 Unable to connect the server, Unauthorised user trying to connect the server"),
	FORBIDDEN_ERROR(403,"[Error] : Error Code : 403 Forbidden Error, Unable to Connect Server!"),
	PAGE_NOT_FOUND(404,"[Error] : Error Code : 404 Server not able to find the resource, Page Not Found!"),
	REQUEST_TIME_OUT(408,"[Error] : Error Code : 408 Request Time out error, Server taking long time to respond."),
	
	PROXY_AUTHENTICATION_REQUIRED(407,"[Error] : Error Code : 407 Failed to " +
			"connect location service as its requires proxy authentication, Please enable authentication proxy!"),
			
	INTERNAL_SERVER_ERROR(500,"[Error] : Error Code : 500 Internal Server Error " +
			"occured, Please check the server log for more details.");

	/**
	 * The <code>statusCode</code> attribute to get the response code
	 * from the location service API.
	 */
	
	private int statusCode;
	
	/**
	 * The <code>statusDescription</code> attribute represents the description
	 * of the response code.
	 */
	
	private String statusDescription;

	/**
	 * The <code>StatusCodes</code> creates instance of <code>StatusCodes</code>
	 * instance and accepts code and description parameters to check the
	 * response code from the location service API.
	 * 
	 * @param statusCode
	 * @param statusDescription
	 */
	
	private StatusCodes(int statusCode, String statusDescription) {
		this.statusCode = statusCode;
		this.statusDescription = statusDescription;
	}
	
	/**
	 * The <code>getStatusCode</code> method returns the code from location
	 * service API.
	 * 
	 * @return the errorCode
	 */
	
	public int getStatusCode() {
		return statusCode;
	}
	
	/**
	 * The <code>getStatusDescription</code> method represents the description
	 * for the response code.
	 *
	 * @return the errorDescription
	 */
	
	public String getStatusDescription() {
		return statusDescription;
	}
	
}
