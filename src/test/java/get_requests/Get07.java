package get_requests;

import base_urls.Jsonplaceholder_BaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends Jsonplaceholder_BaseUrl {

    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

    @Test
    public void get01() {

        // 1. Set the Url
        spec.pathParam("first","todos");

        // 2. Set the expected data
        // 3. Send the requeat and get the response

        Response response=given().spec(spec).when().get("/{first}");
       // response.prettyPrint();
        // 4. Assertions
        response.then().assertThat().statusCode(200);
        // Print all ids greater than 190 on the console
        JsonPath json=response.jsonPath();
        List<Integer> idler=json.getList("findAll{it.id>190}.id");// Groovy Language = Java temelli bir programlama dili
        System.out.println(idler);

        //Assert that there are 10 ids greater than 190
        assertEquals(10, idler.size());

        //Print all userIds whose ids are less than 5 on the console
        List<Integer> userIds=json.getList("findAll{it.id<5}.id");
        System.out.println(userIds);

        //Assert that the number of userIds whose ids are less than 5 is 4
        assertEquals(4,userIds.size());

        //Print all titles whose ids are less than 5
        List<String> titles=json.getList("findAll{it.id<5}.title");
        System.out.println(titles);

        //Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(titles.contains("delectus aut autem"));

    }
}
