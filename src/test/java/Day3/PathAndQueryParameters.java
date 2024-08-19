package Day3;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters {
	//URL = https://reqres.in/api/users?page2&id=5
	@Test
	void testQueryAndPathParamneters() {
		given()
			.pathParam("apiPath", "users")
			.queryParam("page",2)
			.queryParam("id", 5)
		
		.when()
			.get("https://reqres.in/api/{apiPath}")   //rest query parameter will be automatically go with the request
		
		.then()
			.statusCode(200)
			.log().all();
	}
}
