package API_Testing.RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class OAuthTest {
	JsonPath jp;

	@Test
	public void OAuthTestPractise()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\WORK\\WorkSpace\\Driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7en2hk7sugv4vq22hjcfhr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		
		
	String accessTokenResponse=	given().queryParam("code", "")
		.queryParam("client_id", "692183103107-p0m7en2hk7sugv4vq22hjcfhr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYak_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/auth").asString();
	 jp=new JsonPath(accessTokenResponse);
	 String accesstoken=jp.getString("access_token");
		
		
		String response=given().queryParam("acess_token", accesstoken)
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		//.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);

	}

}
