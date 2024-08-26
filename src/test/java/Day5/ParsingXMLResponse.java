package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {

	//@Test(priority=1)
	void testXMLResponse() {
		//Approach 1 - without saving response into a variable
		
		/*given()
				
		.when()
			//.get("https://www.w3schools.com/xml/guestbook.asp")//Content-Type: text/xml
			//.get("https://www.w3schools.com/xml/note.asp")//Content-Type: text/xml
			.get("https://www.w3schools.com/xml/simple.xml")
			
		.then()
			.statusCode(200)
			.header("Content-Type","text/xml")
			.body("breakfast_menu.food[0].name",equalTo("Belgian Waffles"));
			//.log().all();
		*/
		
		//Approach 2 - returning response into a variable and validating node specific values 
		Response res = given()
				
		.when()
			//.get("https://www.w3schools.com/xml/guestbook.asp")//Content-Type: text/xml
			//.get("https://www.w3schools.com/xml/note.asp")//Content-Type: text/xml
			.get("https://www.w3schools.com/xml/simple.xml");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getHeader("Content-Type"), "text/xml");
		Assert.assertEquals(res.xmlPath().get("breakfast_menu.food[0].name").toString(), "Belgian Waffles");
	}
	
	
	@Test(priority=2)
	void testXMLResponseValidate() {
		//Approach 2 - returning response into a variable and validating all node values 
				Response res = given()
						
				.when()
					//.get("https://www.w3schools.com/xml/guestbook.asp")//Content-Type: text/xml
					//.get("https://www.w3schools.com/xml/note.asp")//Content-Type: text/xml
					.get("https://www.w3schools.com/xml/simple.xml");
				
				XmlPath xmlp = new XmlPath(res.asString());
				
				//Verify total number of menu items
				List<String> food_menu = xmlp.getList("breakfast_menu.food");
				Assert.assertEquals(food_menu.size(), 5);
				
				//print all menu items
				List<String> menu_items = xmlp.getList("breakfast_menu.food.name");
				for(String ele : menu_items)
				{
					System.out.println(ele);
				}
				
				//check whether French Toast is available in list or not
				boolean status = false;
				for(String ele : menu_items)
				{
					if(ele.equals("French Toast"))
					{
						status=true;
						System.out.println("Item found : "+ele);
						break;						
					}
				}
				Assert.assertEquals(status,true);	
	}
}
