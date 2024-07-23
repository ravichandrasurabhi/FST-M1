package activities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
 
import org.testng.annotations.Test;
 
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Activity2 {
	String URI = "https://petstore.swagger.io/v2/user";
	 
    @Test(priority=1)
    public void addNewUserFromFile() throws IOException {
        
        FileInputStream inputJSON = new FileInputStream("src/test/java/activities/userinformation.json");
       
        String reqBody = new String(inputJSON.readAllBytes());
 
        Response response = 
            given().contentType(ContentType.JSON)
            .body(reqBody)
            .when().post(URI);
 
        inputJSON.close();
 
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("9098"));
    }
    
    @Test(priority=2)
    public void getUserInfo() {
        
        File outputJSON = new File("src/test/java/activities/userGETResponse.json");
 
        Response response = 
            given().contentType(ContentType.JSON) 
            .pathParam("username", "randomc")
            .when().get(URI + "/{username}");
        
        String resBody = response.getBody().asPrettyString();
 
        try {
            
            outputJSON.createNewFile(); 
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(resBody);
            writer.close();
        } catch (IOException excp) {
            excp.printStackTrace();
        }
        
        response.then().body("id", equalTo(9098));
        response.then().body("username", equalTo("randomc"));
        response.then().body("firstName", equalTo("random"));
        response.then().body("lastName", equalTo("Case"));
        response.then().body("email", equalTo("randomcase@mail.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("9812763450"));
    }
    
    @Test(priority=3)
    public void deleteUser() throws IOException {
        Response response = 
            given().contentType(ContentType.JSON) 
            .pathParam("username", "randomc") 
            .when().delete(URI + "/{username}"); 
 
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("randomc"));
    }
}
