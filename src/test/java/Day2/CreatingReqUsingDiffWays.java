package Day2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


/*
 * Different ways to create a payload[request] are as follows:-
 *1.  Using HashMap
 *2.  Using org.json
 *3.  Using POJO CLass [Plain Old Java Object] :- mostly used, create external class which is cld as POJO class
 *4.  Using external JSON files  
 */

public class CreatingReqUsingDiffWays {
	
	//1. USING HASHMAP :- Hashmap is a java collection in whick value is stored in the form of key-value pair that why suitable for json type of data
	
	@Test
	void testUsingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "TEST1");
		data.put("location","TEST1_Location");
		data.put("phone", "9988776655");
		
		//created java array to add values in the form array using hashmap into the field
		String courseArr[] = {"JAVA","C++"};		
		data.put("courses", courseArr);
		
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name",equalTo("TEST1"))
			.body("location",equalTo("TEST1_Location"))
			.body("phone", equalTo("9988776655"))
			.body("courses[0]",equalTo("JAVA"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	//deleting student record
		@Test(priority=2)
		void testDelete()
		{
			given()
			
			.when()
				.delete("http://localhost:3000/students/680a")
			
			.then()
				.statusCode(200);
					
		}
	
	
}
