package Day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {

	int id;
	@Test(priority=4)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
						
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();    //at last command specify semi-colon and this line will print/log all data on console

	}
	
	@Test(priority=1)
	void createUser()
	{
		HashMap data=new HashMap<String, String>();
		data.put("name","pavan");
		data.put("job","trainer");
		
		
		id = given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
		/*.then()
			.statusCode(201)
			.log().all();*/
	}
	
	
	@Test(priority=2,dependsOnMethods= {"createUser"})
	void updateUser()
	{

		HashMap data=new HashMap();
		data.put("name","john");
		data.put("job","teacher");
		
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.put("https://reqres.in/api/users/"+id)
				
		.then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test(priority=3)
	void deleteUser()
	{
		given()
			
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}
}