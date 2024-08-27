package Day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDeSerialization {

	@Test(priority=1)
	void Serialization() throws JsonProcessingException {
		//Convert POJO to JSON Data
		
		POJO_DataClass pojo_data = new POJO_DataClass();
		pojo_data.setClientName("Postman");
		pojo_data.setClientEmail("Postman@gmail.com");
		
		ObjectMapper objMap = new ObjectMapper();
		String json_data = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(pojo_data);
		
		System.out.println("JSON Data is : \n"+json_data);
	}
	
	@Test(priority=2)
	void DeSerialization() throws JsonProcessingException {
		//Convert JSON to POJO Data
		
		String json_data = "{\r\n"
				+ "  \"clientName\" : \"Postman\",\r\n"
				+ "  \"clientEmail\" : \"Postman@gmail.com\"\r\n"
				+ "}";
		
		ObjectMapper objMap = new ObjectMapper();
		POJO_DataClass pojo_data = objMap.readValue(json_data, POJO_DataClass.class);
		
		System.out.println("Pojo Data is : \nClient Name : "+pojo_data.getClientName()+" \nClient Email : "+pojo_data.getClientEmail());
	}
}
