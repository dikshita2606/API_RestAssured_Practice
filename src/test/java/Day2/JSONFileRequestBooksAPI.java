package Day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class JSONFileRequestBooksAPI {

	@Test(priority=1)
	void registerUserClient() throws FileNotFoundException {
		//to be executed only once in order to generate user/client details
		String filePath = ".\\dataRequest.json";
		System.out.println(filePath);
		File f = new File(filePath);
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject clientDetails = new JSONObject(jt);		
		//Token Created :-
		
		given()
			.contentType("application/json")
			.body(clientDetails.toString())
			.log().all()
			
		.when()
			.post("https://simple-books-api.glitch.me/api-clients/")
		
		.then()
			.statusCode(201)
			.body("accessToken",not(isEmptyOrNullString()))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
	
	//@Test(priority=2)
	void executeAgain() {
		OrgJsonRequestBooksAPI orgjson= new OrgJsonRequestBooksAPI();
		orgjson.executeAgain();
	}
}
