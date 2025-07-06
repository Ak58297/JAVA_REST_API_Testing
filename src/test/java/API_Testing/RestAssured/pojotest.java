package API_Testing.RestAssured;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import API_Testing.RestAssured.Files.Jason_Payload;
import Pojo.Post.getdetails;
import Pojo.Post.locations;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;


public class pojotest {

	
	JsonPath jp;
	String PlaceID;
	@Test
	public void addpojo()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";

       getdetails gd=new getdetails();
		
		locations lo=new locations();
		lo.setLng(38.383494);
		lo.setLat(-38.383494);
		gd.setLocation(lo);
		
		gd.setAccuracy(20);
		gd.setName("abhishek");
		gd.setPhone_number(12345678);

		gd.setAddress("Address line 1");
		
		List<String> l=new ArrayList<>();
		l.add("first");
		l.add("second");
		gd.setTypes(l);
		
		gd.setWebsite("www.google.com");
		gd.setLanguage("English");
		
		System.out.println(gd.getAccuracy());
		
		
		
		
		String response=
				given().queryParam("key", "qaclick123").body(gd)
				.when().post("maps/api/place/add/json")
				.then().statusCode(200).extract().response().asString();
		System.out.println("<------------------->"+response);
		jp=new JsonPath(response);
		PlaceID=jp.getString("place_id");
		System.out.println("Place ID is "+PlaceID);
		
		}
	
	//@Test(dependsOnMethods= {"addpojo"})
	public void getData()
	{
		
		getdetails getResponse=given().queryParam("key", "qaclick123").and().queryParam("place_id", PlaceID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().as(Pojo.Post.getdetails.class);
		
		String web=getResponse.getWebsite();
		System.out.println(jp.getString("website"));
	}
}
