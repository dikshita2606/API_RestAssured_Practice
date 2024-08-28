package Day7;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonObjectAndArray {
	

/* JSON Data on which code was checked
   {
   "menu": 
   {  
	 "id": "file",  
	  "value": "File",  
	  "popup": 
	  {  
	    "menuitem": 
	    [  
	      {"value": "New", "onclick": "CreateDoc()"},  
	      {"value": "Open", "onclick": "OpenDoc()"},  
	      {"value": "Save", "onclick": "SaveDoc()"}  
	    ]  
	  }  
	}
  }  */

	@Test
	void ParseJsonArray() {
		
		Response res = 
				given()
					.contentType(ContentType.JSON)
				
				.when()
					.get("http://localhost:3000/menu");
		
		JSONObject jo = new JSONObject(res.asString());
		
		//Check whether any string is present or not
		boolean flag = false;
		
		for(int i=0;i<jo.getJSONObject("popup").getJSONArray("menuitem").length();i++)
		{
			String bookName = jo.getJSONObject("popup").getJSONArray("menuitem").getJSONObject(i).get("value").toString();
			
			if(bookName.equalsIgnoreCase("save"))
			{
				flag = true;				
				break;
			}
		}
		
		if(flag == true)
		{
			System.out.println("Value found !!!");
		}
		else
		{
			System.out.println("Value not found !!!");
		}
				
	}
}
