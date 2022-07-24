package delete_request;

import base_urls.Jsonplaceholder_BaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends Jsonplaceholder_BaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {

        // 1 - Set the url
        spec.pathParams("first","todos","second",198);

        // 2 - Set the expected data
        Map<String,Object> expectedData=new HashMap<>();

        // 3 - Send delete request and get the response

        Response response= given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        // 4 - Do assertion

        // 1. yol
        Map<String,Object> actualMap=response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedData,actualMap);

        // 2. yol
        assertTrue(actualMap.isEmpty());

        // Delete request yapmadan once "Post Request" yapip bu datayi silmeliyiz
    }
}
