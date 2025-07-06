package API_Testing.RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import API_Testing.RestAssured.Files.*;
public class Practise {
	
	
	String PlaceID;
	JsonPath jp;

	
	
	@Test
	public void AddData()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";

		
	String response=	given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 90,\r\n"
				+ "  \"name\": \"Bengaluru\",\r\n"
				+ "  \"phone_number\": \"(+91) 773 893 3937\",\r\n"
				+ "  \"address\": \"101 street, this is an existing address\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"House\",\r\n"
				+ "    \"Appartment\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"English\"\r\n"
				+ "}\r\n"
				+ "")
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP")).and().body("status", equalTo("OK")).extract().response().asString(); //field level validations
	
		jp=new JsonPath(response);
		PlaceID=jp.getString("place_id");
		System.out.println("Place ID is "+PlaceID);
		
		}
	
	@Test(dependsOnMethods= {"AddData"})
	public void getData()
	{
		String getResponse=given().queryParam("key", "qaclick123").and().queryParam("place_id", PlaceID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200)
		.body("accuracy",equalTo("90")).and().body("name", equalTo("Bengaluru"))
		.extract().response().asString();
		jp=new JsonPath(getResponse);
		System.out.println(jp.getString("website"));
	}

	@Test(dependsOnMethods= {"getData"})
	public void UpdateData()
	{
		String updatedResponse=given().queryParam("key", "qaclick123").body(Jason_Payload.UpdateAddress(PlaceID))
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated")).log().all().extract().response().asString();
	}
}
