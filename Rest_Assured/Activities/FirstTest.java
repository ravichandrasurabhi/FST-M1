package examples;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstTest {
	
	// GET https://petstore.swagger.io/v2/pet/findByStatus?status=alive
		
	@Test
	public void getRequestWithQueryParam() {
		//Send request, save response
		Response response = 
				given().
				baseUri("https://petstore.swagger.io/v2/pet").
				header("Content-Type","application/json").
				queryParam("status", "alive").
				when().
				get("/findByStatus");
		
		//print the headers
		System.out.println(response.getHeaders());
		//print the body
		System.out.println(response.getBody().asString());
		System.out.println("----------------------");
		System.out.println(response.getBody().asPrettyString());
		
		//Extract individual values
		String petStatus = response.then().extract().path("[0].status");
		//Assertions
		Assert.assertEquals(petStatus, "alive");
		
		response.then().statusCode(200).body("[0].status", equalTo("alive"));
	}
	
	// GET https://petstore.swagger.io/v2/pet/{petId}
	@Test
	public void getRequestWithPathParam() {
		
		//Send request, get response and assert with logging
		
		given().
		baseUri("https://petstore.swagger.io/v2/pet").
		header("Content-Type","application/json").
		pathParam("petId", 77232).
		log().all().
		when().
		get("/{petId}").
		then().
		statusCode(200).
		body("status", equalTo("alive")).
		log().all();
		
	}

}
