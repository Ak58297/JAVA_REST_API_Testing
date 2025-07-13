package API_Testing.RestAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import API_Testing.RestAssured.Files.*;
import io.restassured.path.json.JsonPath;

public class Basics {

	public static void main(String[] args) {

		// given: all input details- parameters and body
		// when - submit the api - resource and http method
		// then- validate the respose
		// Window>Preferences>editor>typing> checkboz enable: Escape test when pasting string literal
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		
		//create
		String response = 
				//given().log().all()
				given()
				.queryParam("key", " qaclick123").header("Content-Type","application/json").log().all().body(Jason_Payload.CoursePayload())
				
				.when().post("maps/api/place/add/json")
				
				.then().log().all().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
		
		System.out.println(String.format("Create Response is = %s",response));
		JsonPath jp = new JsonPath(response);  // for parsing Json
		String PlaceID = jp.getString("place_id");
		System.out.println("Create Place id is : " + PlaceID);

		
		// Update the address
		String updatedAddress = "70 walk Jharkhand, this is a updated address";
		String response1 = 
				//given().log().all()
				given()
				.queryParam("key", " qaclick123").body("{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\""+updatedAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
				
				.when().put("maps/api/place/update/json")
				
				.then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"))
				.header("server", "Apache/2.4.52 (Ubuntu)")
				.extract().response().asString();
		System.out.println("Update response is "+response1);
		JsonPath jp2 = new JsonPath(response1);
		String PlaceID1 = jp.getString("place_id");
		System.out.println("Update Place id is : " + PlaceID1);

		// Get Place
		String getPlaceResponse = 
				//given().log().all()
				given()
				.queryParam("key", "qaclick123")
				.queryParam("place_id", PlaceID1)
				
				.when().get("maps/api/place/get/json")
				
				.then().assertThat().log().all().statusCode(200).extract().response().asString();

		System.out.println("Get response is: " + getPlaceResponse);

		JsonPath jp1 = new JsonPath(getPlaceResponse);
		String actual_Address = jp1.getString("address");
		
		Assert.assertEquals(updatedAddress, actual_Address);
		
		
		// Delete the response
		String DelelteResponse=
		given().log().all().queryParam("key", "qaclick123").body("{\r\n"
				+ "    \"place_id\":\""+PlaceID1+"\"\r\n"
				+ "}\r\n"
				+ "")	
		.when().get("maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Delete response is : "+DelelteResponse);
		JsonPath jp3=new JsonPath(DelelteResponse);
		String status=jp3.getString("status");
		System.out.println(status);
		Assert.assertEquals(status, "OK");
		
	

	}

}
