package post_request;

import base_urls.Jsonplaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends Jsonplaceholder_BaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postPojo01() {
        // 1 - Set the Url
        spec.pathParam("first","todos");

        // 2 - Set the expected data
        JsonPlaceHolderPojo requestBody = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        // 3 - Send Post request and get the response
        Response response =given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();
        // 4 - Do assertion
        JsonPlaceHolderPojo actualBody=response.as(JsonPlaceHolderPojo.class);

        assertEquals(requestBody.getUserId(),actualBody.getUserId());
        assertEquals(requestBody.getTitle(),actualBody.getTitle());
        assertEquals(requestBody.getCompleted(),actualBody.getCompleted());

        assertEquals(requestBody.toString(),actualBody.toString());
        System.out.println(requestBody.toString());
        System.out.println(actualBody.toString());


    }
}
