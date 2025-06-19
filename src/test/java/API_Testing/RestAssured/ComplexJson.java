package API_Testing.RestAssured;

import org.testng.Assert;

import API_Testing.RestAssured.Files.Jason_Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {

		JsonPath jp=new JsonPath(Jason_Payload.CoursePayload());
		
		//Print number of cources
		int numberOfCourses=jp.getInt("courses.size()");
		System.out.println(numberOfCourses);
		
		//Print purchase amount
		int purchaseAmount=jp.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);

		//Print title of first course
		String TitleFirstCourse=jp.getString("courses[0].title");
		
		//Print all course titles and their respective price
		for(int i=0;i<numberOfCourses;i++)
		{
			String title=jp.getString(String.format("courses[%s].title",i));
			int price=jp.getInt(String.format("courses[%s].price",i));
			System.out.println(String.format("Course is %s and price is %s", title,price));	
		}
		
		//print number of copies sold by RPA
		System.out.println(jp.getInt("courses[2].copies"));


		
		//Verify if Sum of all Course prices matches with Purchase Amount
				int sum=0;
				for(int i=0;i<numberOfCourses;i++)
				{
					int price=jp.getInt("courses["+i+"].price");
					int NumberOfItem=jp.getInt("courses["+i+"].copies");
					sum=sum+(price*NumberOfItem);
				}
				Assert.assertEquals(sum, purchaseAmount);
		
	}

}
