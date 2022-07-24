package post_request;

import base_urls.Herokuapp_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends Herokuapp_BaseUrl {
    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void postPojo01() {

        // 1 - Set the Url
        spec.pathParam("first","booking");

        // 2 - Set the expected data
        BookingDatesPojo bookingdates = new BookingDatesPojo("2021-09-21","2021-12-21");
        BookingPojo booking=new BookingPojo("Ali","Can",999,true,bookingdates,"Breakfast with white tea, Dragon juice");


        // 3 - Send the post request and get the response

        Response response =given().spec(spec).contentType(ContentType.JSON).body(booking).when().post("/{first}");
        response.prettyPrint();

        // 4 - do assertion
        BookingResponseBodyPojo actualData=response.as(BookingResponseBodyPojo.class);
        assertEquals(200,response.getStatusCode());
        assertEquals(booking.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(booking.getLastname(),actualData.getBooking().getLastname());
        assertEquals(booking.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(booking.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(booking.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(booking.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(booking.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());








    }
}
