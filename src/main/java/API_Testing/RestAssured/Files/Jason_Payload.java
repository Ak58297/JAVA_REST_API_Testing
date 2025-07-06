package API_Testing.RestAssured.Files;

public class Jason_Payload {
	
	public static String AddPayload()
	{
		return "{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Century Villa\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 093 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09: Existing address\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://rahulshettyacademy.com\",\r\n"
				+ "  \"language\": \"English\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String CoursePayload()
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}";
	}
	
	
	public static String DynamicJson( String isbn,String aisle)
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\"Learn API Automation with Java\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\"Jkhn foe\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	
	public static String UpdateAddress(String PlaceID)
	{
		return "{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\"70 walk Jharkhand,=> this is a updated address\",\r\n"
				+ "\"name\":\"Banglore\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}";
	}
	

}
