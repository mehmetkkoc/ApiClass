package put_request;

import base_urls.Jsonplaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends Jsonplaceholder_BaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put01() {

        // 1 - Set the Url
        spec.pathParams("first","todos","second",198);

        // 2 - Set the expected data

        JsonPlaceHolderTestData expectedData=new JsonPlaceHolderTestData();
        Map<String,Object> expectedDataMap=expectedData.expectedDataWithAllKeys(21,"Wash the dishes",false);

        // 3 - Send the request and get the response

        Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().put("/{first}/{second}");
        response.prettyPrint();

        // 4 - Do Assertion

        Map<String,Object> actualDataMap=response.as(HashMap.class);
        assertEquals(response.statusCode(),200);
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));

    }
}
