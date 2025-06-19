package API_Testing.RestAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import API_Testing.RestAssured.Files.Jason_Payload;

import static io.restassured.RestAssured.*;
import  io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Dynamic {
	
	@Test(dataProvider="GetNewJsonDAta")
	public void AddBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
		
		String response=
				given().header("Content-Type", "application/json").body(Jason_Payload.DynamicJson(isbn,aisle))
				.when().post("/Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
				JsonPath jp=new JsonPath(response);
				String id=jp.getString("ID");
				System.out.println(id);
		
	}
	
	@DataProvider(name="GetNewJsonDAta")
	public Object[][] ChangeJsonData()
	{
		return new Object[][] {{"sagf45","123xfdgds"},{"212321","64yrthdsf"},{"123w2dgf","9834t7iehdf"}};
	}

}
