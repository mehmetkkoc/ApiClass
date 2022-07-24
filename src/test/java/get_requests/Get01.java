package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;


public class Get01 {

    /*
        1) Postman manuel API testi icin kullanilir
        2) API otomasyon testi icin Rest-Assure Library kullaniyoruz
        3) Otomasyon kodlarinin yazimi icin su adimlari izliyoruz
            a) Gereksinimleri anlama
            b) Test Case yazmak
                - Test Case yazimi icin "Gherkin Language" kullaniriz
                     'Gherkin' bazi keywordlere sahiptir
                     x) Given: On kosullar
                     y) When: Aksiyonlar--> Get, Put, ...
                     z) Then: Donutler--> Dogrulama, response
                     t) And: Coklu islemler icin

            c) Test codunun yazimi
                i)      Set the URL
                ii)     Set the expected data(POST-PUT-PATCH)
                iii)    Type code to send request
                iiii)   Do Assertion
     */

        /*
        Given
         https://restful-booker.herokuapp.com/booking/55
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
         */

    @Test
    public void get01(){

        // i)      Set the URL
        String url= "https://restful-booker.herokuapp.com/booking/55";

        // ii)     Set the expected data(POST-PUT-PATCH)


        // iii)    Type code to send request
        Response response=given().when().get(url);
        response.prettyPrint();

        // iiii)   Do Assertion
        //response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        //  "Status Code" nasil yazdirilir

        System.out.println("Status Code : "+response.statusCode());

        // "Content Type"

        System.out.println("Content Type : "+response.contentType());

        // "Status Line"

        System.out.println("Status Line : "+response.statusLine());

        // "Header"

        System.out.println(response.header("Connection"));
        System.out.println("===============================");
        System.out.println("Headers :\n"+response.headers());

        // "Time"

        System.out.println("=================================");
        System.out.println("Time : "+response.getTime());


    }
}
