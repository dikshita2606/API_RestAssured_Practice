package Day2;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class OrgJsonRequestBooksAPI {

	//@Test(priority=1)
	void registerUserClient() {
		//to be executed only once in order to generate user/client details
		JSONObject clientDetails = new JSONObject();
		clientDetails.put("clientName", "Test FirstName13");
		clientDetails.put("clientEmail", "testfname130@gmail.com");
		//Token Created :- 216485b98c4ae3d6fb7447ea84fe3dfcc081294c9d50b1806998c0326546ed27
		
		given()
			.contentType("application/json")
			.body(clientDetails.toString()) //when use org.json to create request then we need to convert the request into string and pass it directly we cannot pass it in json format
			
		.when()
			.post("https://simple-books-api.glitch.me/api-clients/")
		
		.then()
			.statusCode(201)
			.body("accessToken",not(isEmptyOrNullString()))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	
	@Test(priority=2)
	void executeAgain() {
		HashMapRequestBooksAPI hashAPI = new HashMapRequestBooksAPI();
		hashAPI.getAPIstatus();
		hashAPI.getSingleBookDetail();
		hashAPI.getListOfBooks();
	}
}
