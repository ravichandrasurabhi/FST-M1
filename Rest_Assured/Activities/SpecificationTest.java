package examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
 
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.HashMap;
import java.util.Map;

/*Saahil Sharma 16-07-2024 09:05 • POST https://petstore.swagger.io/v2/pet
GET https://petstore.swagger.io/v2/pet/{petId}
DELETE https://petstore.swagger.io/v2/pet/{petId}*/

public class SpecificationTest {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	int petId;
	
	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://petstore.swagger.io/v2/pet").
				addHeader("Content-Type", "application/json").build();
		
		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(200).
				expectResponseTime(lessThanOrEqualTo(3000L)).build();
	}
	
	@Test(priority=1)
	public void postRequest() {
		Map<String, Object> reqBody = new HashMap<>();
		reqBody.put("id", 23456);
		reqBody.put("name", "petPet");
		reqBody.put("status", "alive");
		//Send request, save response
		Response response = given().spec(requestSpec).body(reqBody).when().post();
		
		//Extract the petId
		petId = response.then().extract().path("id");
		
		//Assertions
		response.then().spec(responseSpec);
	}
	
	@Test(priority=2)
	public void getRequest() {
		given().spec(requestSpec).pathParam("petId", petId).log().uri().
		when().get("/{petId}").
		then().spec(responseSpec).body("status",equalTo("alive")).log().all();
	}
	
	@Test(priority=3)
	public void deleteRequest() {
		given().spec(requestSpec).pathParam("petId", petId).log().uri().
		when().delete("/{petId}").
		then().spec(responseSpec).body("message",equalTo(""+petId)).log().all();
	}
}
