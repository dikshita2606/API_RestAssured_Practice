package Day3;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;


public class CookiesDemo {

	//@Test(priority=1)
	void testCookies() {
		
		given()
		
		.when()
			.get("https://www.google.com")
		.then()
			.cookie("AEC","AVYB7cqSV_08_oklQXhseG74CX1phJy2TZ6-Sg2HqivNOr6iGGdenGWzOg")
			.log().all();
	}
	
	@Test(priority=2)
	void gettestCookiesInfo() {
		
		Response res = given()  //res will store all the response after hitting url
		
		.when()
			.get("https://www.google.com");
		/*.then()
			//.cookie("AEC","AVYB7cqSV_08_oklQXhseG74CX1phJy2TZ6-Sg2HqivNOr6iGGdenGWzOg")
			.log().all();*/
		
		String cookie_AEC_value = res.getCookie("AEC");
		System.out.println("Cookie AEC Value is :- "+cookie_AEC_value);
		
		//get all cookies
		Map<String,String> cookies_value = res.getCookies();
		
		//printing all keys
		System.out.println(cookies_value.keySet());
		
		//printing all keys and values
		for(String key:cookies_value.keySet())
		{
			String cookie_Val = res.getCookie(key);
			System.out.println(key+"    "+cookie_Val);
		}
	}
}
