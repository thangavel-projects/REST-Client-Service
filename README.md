# RestClientService

This is a REST Service Client Project with Proper Design and supports multilingual which covers JUnit Test Cases.

Assuming a REST Server returns few details location data in the JSON format, invoke that service and get the location data and
write in CSV file. For Instance If you pass a city details to service say London, the city details returns in the format of JSON Object.

The REST Client Service reads the JSON Object and Write it in CSV and Generates Cobetura reports in target folder of Maven.

You need to add the proper URL in the LocationServiceHelper.java class and run below commands. The CSV file would be generated in User Home
Directory.

`mvn clean compile cobertura:cobertura assembly:single`
