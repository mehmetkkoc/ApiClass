package get_requests;

import base_urls.Herokuapp_BaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import test_data.HerokuappTestData;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends Herokuapp_BaseUrl {
    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                        "firstname": "Oliver",
                        "lastname": "Smith",
                        "totalprice": 100,
                        "depositpaid": true,
                        "bookingdates": {
                            "checkin": "2022-07-18",
                            "checkout": "2022-07-19"
                        },
                        "additionalneeds": "Breakfast"
}
     */

    @Test
    public void get01() {

        // 1 - Set the Url
        spec.pathParams("first","booking","second",22);

        // 2 - Set th eexpected data
        HerokuappTestData inString=new HerokuappTestData();
        String expectedData=inString.expectedDataInString("Oliver","Smith",100,true,"2022-07-18","2022-07-19","Breakfast");

        BookingPojo expectedDataPojo=JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);
        System.out.println(expectedData);
        // 3 - Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4 - Do assertions
        BookingPojo actualDataPojo=JsonUtil.convertJsonToJavaObject(response.asString(),BookingPojo.class);

        // Hard Assertion
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());
        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());

        // Soft Assertion
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(actualDataPojo.getFirstname(),expectedDataPojo.getFirstname(),"firstname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getLastname(),expectedDataPojo.getLastname(),"lastname uyusmadi");
        softAssert.assertEquals(actualDataPojo.getTotalprice(),expectedDataPojo.getTotalprice(),"totalprice uyusmadi");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(),expectedDataPojo.getDepositpaid(),"Depositpaid uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(),expectedDataPojo.getBookingdates().getCheckin(),"checkin uyusmadi");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(),expectedDataPojo.getBookingdates().getCheckout(),"checkout uyusmadi");
        softAssert.assertEquals(actualDataPojo.getAdditionalneeds(),expectedDataPojo.getAdditionalneeds(),"additinional needs uyusmadi");
        softAssert.assertAll();
    }
}
