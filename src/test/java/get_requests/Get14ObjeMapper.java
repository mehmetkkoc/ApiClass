package get_requests;

import base_urls.Jsonplaceholder_BaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import test_data.JsonPlaceHolderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjeMapper extends Jsonplaceholder_BaseUrl {
    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    // 1. Yol

    @Test
    public void get01ObjectMapper() {
        // 1 - Set the Url
        spec.pathParams("first","todos","second","198");

        // 2 - Set the expected data
        String expectedData="{\n" +
                "\t\t\t\t\t\t\t\t\t    \"userId\": 10,\n" +
                "\t\t\t\t\t\t\t\t\t    \"id\": 198,\n" +
                "\t\t\t\t\t\t\t\t\t    \"title\": \"quis eius est sint explicabo\",\n" +
                "\t\t\t\t\t\t\t\t\t    \"completed\": true\n" +
                "\t\t\t\t\t\t\t\t\t  }";
        HashMap<String, Object> expectedDataMap=JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);
        System.out.println(expectedDataMap);

        // 3 - Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");

        // 4 - Do asstertion
        HashMap<String, Object> actualDataMap=JsonUtil.convertJsonToJavaObject(response.asString(),HashMap.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));

    }


    // 2. Yol

    @Test
    public void get02Pojo() {
        // 1 - Set the Url
        spec.pathParams("first","todos","second","198");

        // 2 - Set the expected data
        JsonPlaceHolderTestData inString=new JsonPlaceHolderTestData();
        String expectedData=inString.expectedDataInString(10,"quis eius est sint explicabo",true);
        JsonPlaceHolderPojo expectedDataPojo=JsonUtil.convertJsonToJavaObject(expectedData, JsonPlaceHolderPojo.class);
        System.out.println(expectedDataPojo);

        // 3 - Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");

        // 4 - Do asstertion
        JsonPlaceHolderPojo actualDataPojo=JsonUtil.convertJsonToJavaObject(response.asString(),JsonPlaceHolderPojo.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getUserId(),actualDataPojo.getUserId());
        assertEquals(expectedDataPojo.getTitle(),actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(),actualDataPojo.getCompleted());

    }
}
