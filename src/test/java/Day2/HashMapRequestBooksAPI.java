package Day2;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HashMapRequestBooksAPI {
	
	
	
	@Test(priority=1)
	void registerUserClient() {
		//to be executed only once in order to generate user/client details
		HashMap clientDetails = new HashMap();
		
		clientDetails.put("clientName", "Test FirstName15");
		clientDetails.put("clientEmail", "testfname15@gmail.com");
		//Token Created:- 2a2b5b3b5cd98d87f796f282dba0eca08c4e4b4c8e74df612068f9b4dc76c480
		
		given()
			.contentType("application/json")
			.body(clientDetails)
			.log().all()
			
		.when()
			.post("https://simple-books-api.glitch.me/api-clients/")
		
		.then()
			.statusCode(201)
			.body("accessToken",not(isEmptyOrNullString()))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
		
			//String accessToken = response.jsonPath().getString("accessToken");
			//Assert.assertEquals(accessToken, not(isEmptyOrNullString()));
	}
	
	
	@Test(priority=2)
	public void getAPIstatus() {
		
		 given()
			.contentType("application/json")
			
		.when()
			.get("https://simple-books-api.glitch.me/status")
			
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
		
	}

	
	@Test(priority=3)
	public void getListOfBooks() {
		 given()
			.contentType("application/json")
			
		.when()
			.get("https://simple-books-api.glitch.me/books")
			
		.then()
			.statusCode(200)
			.body("$.size()", equalTo(6)) //validate the size of the response
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	@Test(priority=4)
	public void getSingleBookDetail() {
		given()
		.contentType("application/json")
		
	.when()
		.get("https://simple-books-api.glitch.me/books/4")
		
	.then()
		.statusCode(200)
		.body("id", equalTo(4))
		.body("name", equalTo("The Midnight Library"))
		.body("type", equalTo("fiction"))
		.body("available",equalTo(true)) //boolean value check not passed as string
		.header("Content-Type", "application/json; charset=utf-8")
		.log().all();
	}
}
