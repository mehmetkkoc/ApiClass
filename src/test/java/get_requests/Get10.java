package get_requests;

import base_urls.Gorest_BaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GorestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get10 extends Gorest_BaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/13
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
    "meta": null,
    "data": {
        "id": 13,
        "name": "Mani Kaur",
        "email": "mani_kaur@kiehn.co",
        "gender": "male",
        "status": "inactive"
    }
}
     */

    @Test
    public void get01() {

        // 1 - set the Url

        spec.pathParams("first","users","second",13);

        // 2 - Set the expected data

        GorestTestData dataKey = new GorestTestData();
        Map<String, String> dataKeyMap=dataKey.dataKeyMap("Mani Kaur","mani_kaur@kiehn.co","male","inactive");

        Map<String,Object> expectedData=dataKey.expectedDataMap(null,dataKeyMap);


        // 3 - Send the request and get the response

        Response response =given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData=response.as(HashMap.class);

        // 4 - Do Assertion

        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"),((Map)actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"),((Map)actualData.get("data")).get("status"));
        response.then().assertThat().statusCode(200);



    }
}
