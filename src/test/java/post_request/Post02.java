package post_request;

import base_urls.Herokuapp_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerokuappTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends Herokuapp_BaseUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "John",
                 "lastname": "Doe",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2020-09-09",
                     "checkout": "2020-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "bookingid": 5315,
                                                "booking": {
                                                    "firstname": "John",
                                                    "lastname": "Doe",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2020-09-09",
                                                        "checkout": "2020-09-21"
                                                    }
                                                }
                                             }
     */

    @Test
    public void post01() {
        // 1 - Set the Url
        spec.pathParam("first","booking");

        // 2 - Set the expected data
        HerokuappTestData herokuapp = new HerokuappTestData();
        Map<String, String> bookingdatesMap=herokuapp.bookingDatesSetUp("2020-09-09","2020-09-21");
        Map<String,Object> expectedDataMap=herokuapp.expectedDataSetup("John","Doe",11111,true,bookingdatesMap);

        // 3 - Send the request and get the response
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        // 4 - Do Assertion
        Map<String,Object> actualDataMap=response.as(HashMap.class);

        assertEquals(response.statusCode(),200);
        assertEquals(expectedDataMap.get("firstname"),((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"),((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"),((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"),((Map)actualDataMap.get("booking")).get("depositpaid"));
        assertEquals(bookingdatesMap.get("checkin"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"),((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }
}
