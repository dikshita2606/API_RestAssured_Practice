package Day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {
	
	//@Test(priority=1)
	void SingleFileUpload() {
		
		File fs = new File("D:\\Learning Folder\\GitCommands.pdf");
		
		given()
			//specify the form -data 
			.multiPart("file",fs)
			.contentType("multipart/form-data")
		
		.when()
			.post("http://localhost:8080/uploadFile")
		
		.then()	
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("fileName",equalTo("GitCommands.pdf"))
			.log().all();
		
	}
	
	@Test(priority=1)
	void multipleFileUpload() {			
			File fs1 = new File("D:\\Learning Folder\\GitCommands.pdf");
			File fs2 = new File("D:\\Learning Folder\\Books+API.pdf");
			
			//File fileArr[] = {fs1,fs2};
			given()
				//specify the form -data 
				.multiPart("files",fs1)
				.multiPart("files",fs2)
			
				//.multiPart("files",fileArr)
				.contentType("multipart/form-data")
			
			.when()
				.post("http://localhost:8080/uploadMultipleFiles")
			
			.then()	
				.statusCode(200)
				.body("[0].fileName", equalTo("GitCommands.pdf"))	
				.body("[1].fileName", equalTo("Books+API.pdf"))		
				.log().all();
	}
	
	
	@Test(priority=2)
	void fileDownload() {
		given()
		
		.when()
			.get("http://localhost:8080/downloadFile/GitCommands.pdf")
			
		.then()
				.statusCode(200)
				.header("Content-Type","application/pdf");
				//.log().all();
	}

}
