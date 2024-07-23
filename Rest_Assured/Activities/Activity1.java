package activities;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity1 {
	
	String URI = "https://petstore.swagger.io/v2/pet";
	
  @Test(priority=1)
  public void postRequest() {
	  
	  String reqBody = "{"
	  		+ "  \"id\": 77232,"
	  		+ "  \"name\": \"Riley\","
	  		+ "  \"status\": \"alive\""
	  		+ "}";
	  
	 Response response = 
			 given().
			 header("Content-Type","application/json").
			 body(reqBody).
			 when().post(URI);
	 
	 response.then().body("id", equalTo(77232));
	 response.then().body("name", equalTo("Riley"));
	 response.then().body("status", equalTo("alive"));
	  
  }
  
  @Test(priority=2)
  public void getRequest() {
	  Response response = 
			  given().
			  header("Content-Type","application/json").
			  when().
			  pathParam("petId", "77232").
			  get(URI + "/{petId}");
	  
	  response.then().body("id", equalTo(77232));
	  response.then().body("name", equalTo("Riley"));
	  response.then().body("status", equalTo("alive"));
  }
  
  @Test(priority=3)
  public void deleteRequest() {
	  	Response response = 
	  			given().
				  header("Content-Type","application/json").
				  when().
				  pathParam("petId", "77232").
				  delete(URI + "/{petId}");
	  	
	  	response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("77232"));
  }
}
