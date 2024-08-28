package Day7;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

/*
 * Authentication Types in Rest Assured
 * 1. Basic Authentication
 * 2. Digest Authentication
 * 3. Preemptive Authentication
 * 4. Bearer Token
 * 5. OAuth 1.0 and 2.0
 * 6. API Key
 * */

public class Authentication {

	//Basic Auth
	@Test(priority=1)
	void testBasicAuthentication() {
		
		given()
			.auth().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	//Digest Auth
	@Test(priority=2)
	void testDigestAuthentication() {
		
		given()
			.auth().digest("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("authenticated", equalTo(true))
			.log().all();
		
	}
	
	//Preemptive Auth
	@Test(priority=3)
	void testPreemptiveAuthentication() {
		
		given()
			.auth().preemptive().basic("postman", "password")
		
		.when()
			.get("https://postman-echo.com/basic-auth")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.body("authenticated", equalTo(true))
			.log().all();		
	}
	
	//Bearer Token
	@Test(priority=4)
	void testBearerAuthentication() {
		
		String bearerToken ="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";
		given()
			.headers("Authorization","Bearer "+bearerToken)
		
		.when()
			.get("https://api.github.com/users/repos")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.header("Server", "github.com")
			.body("id", equalTo(77416))
			.log().all();		
	}
	
	//OAuth 1.0 and 2.0
	//@Test(priority=5)
	void testOAuthAuthentication() {
		
		given()
			.auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")  //oAuth1.0
			.auth().oauth2("accessToken")  //oAuth2.0
		
		.when()
			.get("https://api.github.com/users/repos")
		
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json; charset=utf-8")
			.header("Server", "github.com")
			.body("id", equalTo(77416))
			.log().all();		
	}
	
	//AI Key Authentiction
	@Test(priority=5)
	void test_API_Key_Authentication() {
		//Method 1
		/*given()
			.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c") //appid is APIKey
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		.then()
			.statusCode(200)
			.log().all();	*/
		
		//Method2
		
		given()
			.queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")			
			.pathParam("mypath","data/2.5/forecast/daily")		
			.queryParam("q", "Delhi")			
			.queryParam("units", "metric")			
			.queryParam("cnt", "7")			
			
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		
		.then()
			.statusCode(200);
			//.log().all();
				
	}
	
}
