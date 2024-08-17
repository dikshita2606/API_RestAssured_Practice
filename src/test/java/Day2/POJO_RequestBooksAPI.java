package Day2;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class POJO_RequestBooksAPI {

	@Test(priority=1)
	void registerUserClient() {
		//to be executed only once in order to generate user/client details
		POJO_DataCreationClass clientDetails = new POJO_DataCreationClass();
		clientDetails.setClientName("TEMP FName1");
		clientDetails.setClientEmail("tempfname1@temp.com");
		
		//Token Created :- d52fb457d5544ed914bd36f72a9b3b9332fc3618fbdbf4d3ced72b19e7cf563f
		
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
	}
	
	
	@Test(priority=2)
	void executeAgain() {
		OrgJsonRequestBooksAPI orgjson= new OrgJsonRequestBooksAPI();
		orgjson.executeAgain();
	}
}
