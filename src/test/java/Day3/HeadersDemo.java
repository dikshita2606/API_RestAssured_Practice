package Day3;

import org.testng.annotations.Test;
import io.restassured.http.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class HeadersDemo {

	//@Test(priority=1)
		void testheader() {
			
			given()
			
			.when()
				.get("https://www.google.com")
			.then()
				.header("Content-Type","text/html; charset=ISO-8859-1")
				.and()
				.header("Content-Encoding", "gzip")
				.and()
				.header("Server", "gws")
				.log().all();
		}
		
		@Test(priority=2)
		void getHeaders()
		{
		
			Response res=	given()
					
				.when()
					.get("https://www.google.com/");
					
			//get single header info
			String headervalue=res.getHeader("Content-Type");
			System.out.println("The value of Content-type header is:"+headervalue);
			
			//get all headers info
			Headers myheaders=res.getHeaders();
			
			for(Header hd:myheaders)
			{
				System.out.println(hd.getName()+"	"+hd.getValue());
			}
			
		}
}
