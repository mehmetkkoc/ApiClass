package patch_request;

import base_urls.Jsonplaceholder_BaseUrl;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;


public class Patch01 extends Jsonplaceholder_BaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void patch01() {

        // 1 - Set the Url
        spec.pathParams("first","todos","second",198);

        // 2 - Set the request body

        JsonPlaceHolderTestData requestBody=new JsonPlaceHolderTestData();
        Map<String,Object> requestBodyMap=requestBody.expectedDataWithMissingKeys(null,"Hello World",null);

        // 3 - Send the patch request

        Response response=given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        // 4 - Do Assertion
        Map<String,Object> actualDataMap=response.as(HashMap.class);
        assertEquals(response.statusCode(),200);
        assertEquals(requestBodyMap.get("title"),actualDataMap.get("title"));

        response.then().assertThat().statusCode(200).body("title",equalTo(requestBodyMap.get("title")));
    }
}
