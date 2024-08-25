package Day4;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

import io.restassured.response.Response;

public class ValidateJsonResponse {
	/*There are different ways of validating response   
	 * 1. Directly validate using .body method and equalTo ,method
	 * 2. Storing response in a variable and then validating it using TestNG assertion
	 * 3. Storing response and then validating using JSONPath, JSONObject 
	 * in 3rd hierarchy will be followed as JSONObject > JSONArray > JSONObject 
	 */

	
	@Test(priority=1)
	void JsonValidation() {
		
		given()
			.contentType("ContentType.JSON")
		
		.when()
			.get("http://localhost:3000/book")
				
		.then()
			.statusCode(200)
			.body("[2].name", equalTo("The Vanishing Half"))
			.log().all();
		
	}
	
	@Test(priority=2)
	void JsonResponseValidate() {
		
		Response res = 
		given()
			.contentType(ContentType.JSON)
			
		.when()
			.get("http://localhost:3000/book");
		
		Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json");
		
		String bookname = res.jsonPath().get("[2].name").toString();
		System.out.println("Book name : "+bookname);
		//String exp = "The Vanishing Half";
		Assert.assertEquals(bookname, "The Vanishing Half");
		
		/*JSONObject jo = new JSONObject(res.asString());
		
		for(int i =0;i<jo.getJSONArray("book").length();i++)
		{
			String name = jo.getJSONArray("book").getJSONObject(i).get("name").toString();
			System.out.println(name);
		}*/
			
	}
}
