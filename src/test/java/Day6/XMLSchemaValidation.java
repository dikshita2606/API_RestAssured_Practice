package Day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class XMLSchemaValidation {

	@Test
	void xmlSchemaValidation(){
		//generate xml schema 
		//https://www.convertsimple.com/convert-xml-to-xsd-xml-schema/
		
		given()
		
		.when()
			.get("https://www.w3schools.com/xml/simple.xml")
			
		.then()
			.statusCode(200)
			.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("XMLSchema.xsd"));
	}
}
