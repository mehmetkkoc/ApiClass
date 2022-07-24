package get_requests;

import base_urls.Herokuapp_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends Herokuapp_BaseUrl {
    /*
    Given
            https://restful-booker.herokuapp.com/booking/555
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
    "firstname": "\"Massimo\"",
    "lastname": "\"Wiegel\"",
    "totalprice": 411,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-07-17",
        "checkout": "2022-07-27"
    },
    "additionalneeds": "\"Dinner\""
}
     */

    @Test
    public void Get01() {
         // 1- Set the URL
        spec.pathParams("first","booking","second",555);

        // 2- Set the expected data
        // 3- Send request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4- Do Assertions

        //1. Yol
        response.then().assertThat()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .body("firstname",equalTo("\"Massimo\"")
                                        ,"lastname", equalTo("\"Wiegel\"")
                                        ,"totalprice", equalTo(411)
                                        ,"depositpaid",equalTo(true)
                                        ,"bookingdates.checkin",equalTo("2022-07-17")
                                        ,"bookingdates.checkout",equalTo("2022-07-27")
                                        ,"additionalneeds",equalTo("\"Dinner\""));

        //2. Yol: JasonPath Class kullanilir

        JsonPath json=response.jsonPath();
        assertEquals("\"Massimo\"", json.getString("firstname"));
        assertEquals("\"Wiegel\"", json.getString("lastname"));
        assertEquals(411, json.getInt("totalprice"));
        assertEquals(true, json.getBoolean("depositpaid"));
        assertEquals("2022-07-17", json.getString("bookingdates.checkin"));
        assertEquals("2022-07-27", json.getString("bookingdates.checkout"));
        assertEquals("\"Dinner\"", json.getString("additionalneeds"));

        // 3. Yol: Soft Assertion
        // Soft assertion icin 3 adim izlenir

        // 1) Soft assert objesi olusturulur
        SoftAssert softAssert=new SoftAssert();

        // 2) Obje araciligi ile assert yapilir
        softAssert.assertEquals(json.getString("firstname"),"\"Massimo\"","firstname uyusmadi");
        softAssert.assertEquals(json.getString("lastname"),"\"Wiegel\"");
        softAssert.assertEquals(json.getInt("totalprice"),411);
        softAssert.assertEquals(json.getBoolean("depositpaid"),true);
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-07-17");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-07-27");
        softAssert.assertEquals(json.getString("additionalneeds"),"\"Dinner\"");

        // 3)assertAll() methodu kullanilir. Aksi takdirde kod her zaman pass olur
        softAssert.assertAll();
    }
}
