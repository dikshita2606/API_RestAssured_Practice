package Day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerate {
	
	@Test
	void testDataGenerate() {
		Faker f = new Faker();
		
		//generate random data and print values
		
		System.out.println("Full Name :- "+f.name().fullName());
		System.out.println("First Name :- "+f.name().firstName());
		System.out.println("Last Name :- "+f.name().lastName());
		System.out.println("UserName :- "+f.name().username());
		System.out.println("Pssword :- "+f.internet().password());
		System.out.println("Phone Number :- "+f.phoneNumber().cellPhone());
		System.out.println("Email Address :- "+f.internet().safeEmailAddress());
	
	}
}
