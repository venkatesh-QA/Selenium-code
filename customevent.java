import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class customevent {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://g6zp7n-api.qa.responsys.ocs.oc-test.com";
		String Response=given().log().all().headers("Content-Type","application/x-www-form-urlencoded")
		.body("user_name=ocigrmbr726&password=Welcome1234!&auth_type=password").when().post("rest/api/v1/auth/token")
		.then().log().all().statusCode(200).extract().response().asString();
		System.out.println(Response);
		JsonPath js=new JsonPath(Response);
		String authToken=js.get("authToken");
		System.out.println("Auth token is "+ authToken);
		String endPoint=js.get("endPoint");
		System.out.println("end point is "+ endPoint);
	
String customeventresponse=given().log().all().headers("Content-Type","application/json").
headers("Authorization", authToken).body("{\r\n"
		+ "\"customEvent\" : {\r\n"
		+ "\"eventNumberDataMapping\" : null,\r\n"
		+ "\"eventDateDataMapping\" : null,\r\n"
		+ "\"eventStringDataMapping\" : null\r\n"
		+ "},\r\n"
		+ "\"recipientData\" : [{\r\n"
		+ "\"recipient\" : {\r\n"
		+ "\"customerId\" : null,\r\n"
		+ "\"emailAddress\" : \"boinapelly.venkat@yahoo.com\",\r\n"
		+ "\"listName\" : {\r\n"
		+ "\"folderName\" : \"venkat\",\r\n"
		+ "\"objectName\" : \"contacts-venkat\"\r\n"
		+ "},\r\n"
		+ "\"recipientId\" : null,\r\n"
		+ "\"mobileNumber\" : null,\r\n"
		+ "\"emailFormat\" : \"HTML_FORMAT\"\r\n"
		+ "},\r\n"
		+ "\"optionalData\" : [{\r\n"
		+ "\"name\" : \"NON_UTF\",\r\n"
		+ "\"value\" : \"ghfgg\"\r\n"
		+ "}\r\n"
		+ "]\r\n"
		+ "}\r\n"
		+ "]\r\n"
		+ "}").when().post("/rest/api/v1.3/events/customeventmigration").then().extract().response().asString();
System.out.println(customeventresponse);
JsonPath js1=new JsonPath(customeventresponse);
String riid=js1.get("recipientId").toString();
System.out.println(riid);

	}

}
