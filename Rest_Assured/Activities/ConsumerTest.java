package project;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

@ExtendWith(PactConsumerTestExt.class)
public class ConsumerTest {
	//Set the headers
	Map<String, String> headers = new HashMap<>();
	
	//Create the contract (Pact)
	@Pact(consumer = "UserConsumer", provider = "UserProvider")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
		//Set the headers
		headers.put("Content-Type", "application/json");
		//Create request and response body
		DslPart requestResponseBody	= new PactDslJsonBody().
				numberType("id", 123).
				stringType("firstName", "fname").
				stringType("lastName", "lname").
				stringType("email", "my@email.com");
		//Create the contract
		return builder.given("POST Request").
				uponReceiving("Request to create a user").
				method("POST").
				path("/api/users").
				headers(headers).
				body(requestResponseBody).
				willRespondWith().
				status(201).
				body(requestResponseBody).
				toPact();
	}
	
	//Consumer test with mock provider
	@Test
	@PactTestFor(providerName = "UserProvider", port = "8282")
	public void postRequestTest() {
		//Create a request body
		Map<String, Object> reqBody = new HashMap<>();
		reqBody.put("id", 123);
		reqBody.put("firstName", "fname");
		reqBody.put("lastName", "lname");
		reqBody.put("email", "my@email.com");
		//Send a request, get response, assert response
		given().baseUri("http://localhost:8282/api/users").headers(headers).body(reqBody).log().all().
		when().post().
		then().statusCode(201).body("id", equalTo(123)).log().all();
	}
}
