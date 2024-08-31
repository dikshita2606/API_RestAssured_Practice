package Day8;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;


public class GetUserDetails {

	@Test
	void testGetUserDetails(ITestContext context) {
		int userId = (Integer) context.getAttribute("userId");
		String BrearToken = "a99f869fd75cb7e84f8be876497c5af719d0471f7544920203454236153349f4";
		
		given()
			.headers("Authorization","Bearer "+BrearToken)
			.pathParam("userId",userId)
		
		.when()
			.get("https://gorest.co.in/public/v2/users/{userId}")
		
		.then()
			.statusCode(200)
			.log().body();
			//.log().all();
	}
}
