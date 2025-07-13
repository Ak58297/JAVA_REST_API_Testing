package API_Testing.RestAssured;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import API_Testing.RestAssured.Files.Jason_Payload;
import Pojo.Get_PojoClasses.getResponse;
import Pojo.Post.getdetails;
import Pojo.Post.locations;
import Pojo.Update_PojoClasses.Update_Address;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


public class SpecBuilder {

	
	JsonPath jp;
	String PlaceID;
	@Test(priority=0)
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
		
		List<String> l= new ArrayList<String>() ;
		l.add("Firstname");
		l.add("Lastname");

		gd.setTypes(l);
		
		gd.setWebsite("www.google.com");
		gd.setLanguage("English");
		
		System.out.println(gd.getAccuracy());
		
		RequestSpecification  specobjt=new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		//RequestSpecification vs ResponseSpecification
		//- RequestSpecification: Defines base URI, headers, content type.
		// ResponseSpecification: Defines expected status code, headers, body structur

		
		String response=
				given().spec(specobjt)
				.body(gd)
				.when().post("maps/api/place/add/json")
				.then().statusCode(200).extract().response().asString();
		System.out.println("<------------------->"+response);
		jp=new JsonPath(response);
		PlaceID=jp.getString("place_id");
		System.out.println("Place ID is "+PlaceID);
		}
	
	@Test(priority=1)
	public void getData()
	{
		
		String getResponse=given().queryParam("key", "qaclick123").and().queryParam("place_id", PlaceID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();
		jp=new JsonPath(getResponse);

		//String web=getResponse.getWebsite();
		System.out.println(getResponse);
		System.out.println(jp.getString("website"));
	}
	
	@Test(dependsOnMethods= {"addpojo"}, priority=2)
	public void updatedata()
	{
		Update_Address au=new Update_Address();	
		au.setPlace_id(PlaceID);
		au.setAddress("Update address => Bengaluru");
		au.setKey("qaclick123");
		
		
		
		String UpdatedResponse=given().queryParam("key", "qaclick123").log().all().body(au)
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).log().all().body("msg", equalTo("Address successfully updated")).extract().response().asString();
		
		JsonPath jp=new JsonPath(UpdatedResponse);
		String Rmsg=jp.getString("msg");
		System.out.println("=============================================="+Rmsg);
		Assert.assertEquals("Address successfully updated", Rmsg);
		
		
	}
//	
//	@Test(priority=3)
//	public void getData2()
//	{
//		
//		getResponse getResponse=given().queryParam("key", "qaclick123").and().queryParam("place_id", PlaceID)
//		.when().get("maps/api/place/get/json")
//		.then().assertThat().statusCode(200).extract().response().as(Pojo.Get_PojoClasses.getResponse.class);
//		
//		String web=getResponse.getWebsite();
//		System.out.println(web);
//		System.out.println(jp.getString("website"));
//	}
}
