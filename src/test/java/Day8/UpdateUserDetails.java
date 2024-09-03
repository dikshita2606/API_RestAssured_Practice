package Day8;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUserDetails {
	@Test
	void testUpdateUserDetails(ITestContext context) {
		//int userId =  (Integer) context.getAttribute("userId");
		int userId =  (Integer) context.getSuite().getAttribute("userId");
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "Active");
		System.out.println(data);
		
		String BrearToken = "a99f869fd75cb7e84f8be876497c5af719d0471f7544920203454236153349f4";
		
		given()
			.headers("Authorization","Bearer "+BrearToken)
			.contentType("application/json")
			.pathParam("userId",userId)
			.body(data.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{userId}")
		
		.then()
			.statusCode(200)
			.body("id", equalTo(userId))
			.log().body();
			//.log().all();
		
		
		
		/*{
		    "id": 7376633,
		    "name": "Dr. Virgil Zboncak",
		    "email": "zandra.robel@hotmail.com",
		    "gender": "female",
		    "status": "inactive"
		  }*/
	}


}
