package get_requests;

import base_urls.Gorest_BaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestDataPojo;
import pojos.GorestResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends Gorest_BaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
                    "meta": null,
                    "data": {
                        "id": 2508,
                        "name": "Fr. Dhanvin Malik",
                        "email": "dhanvin_fr_malik@runolfsson-braun.com",
                        "gender": "female",
                        "status": "inactive"
             }
        }
     */

    @Test
    public void getPojo01() {

        // 1 - Set the Url
        spec.pathParams("first","users","second","2508");

        // 2 - Set the expected data
        GorestDataPojo gorestDataPojo=new GorestDataPojo(2508,"Fr. Dhanvin Malik","dhanvin_fr_malik@runolfsson-braun.com","female","inactive");
        GorestResponseBodyPojo gorestResponseBodyPojo=new GorestResponseBodyPojo(null,gorestDataPojo);

        // 3 - Send the request and get the response

        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        GorestResponseBodyPojo actualData=response.as(GorestResponseBodyPojo.class);

        response.then().assertThat().statusCode(200);
        assertEquals(gorestResponseBodyPojo.getMeta(),actualData.getMeta());
        assertEquals(gorestDataPojo.getName(),actualData.getData().getName());
        assertEquals(gorestDataPojo.getId(),actualData.getData().getId());
        assertEquals(gorestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(gorestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(gorestDataPojo.getStatus(),actualData.getData().getStatus());




    }
}
