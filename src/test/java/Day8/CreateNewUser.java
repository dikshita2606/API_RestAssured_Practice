package Day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class CreateNewUser {

	@Test
	void testCreateNewUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Female");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Inactive");
		System.out.println(data);
		
		String BrearToken = "a99f869fd75cb7e84f8be876497c5af719d0471f7544920203454236153349f4";
		
		int id = given()
			.headers("Authorization","Bearer "+BrearToken)
			.contentType("application/json")
			.body(data.toString())
			
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");			

		
		System.out.println("ID :- "+id);
				
		// Chain (Share Data with) APIs in same Test Tag
		//context.setAttribute("userId", id);;
				
		// Chain (Share Data with) APIs in same Suite Tag
		context.getSuite().setAttribute("userId", id);	
		
	}
}
