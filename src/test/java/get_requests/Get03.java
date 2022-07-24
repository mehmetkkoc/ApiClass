package get_requests;

import base_urls.Jsonplaceholder_BaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class Get03 extends Jsonplaceholder_BaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */
    @Test
    public void get01(){
        // 1- Set theUrl
        // String url="https://jsonplaceholder.typicode.com/todos/23";  -- Onerilmiyor

        spec.pathParams("first","todos","second",23);

        // 2- Set the expected data
        // 3- Send the request and get the response

        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4- Do assertion

        // 1. yol
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json")
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"))
                .body("completed",equalTo(false))
                .body("userId",equalTo(2));

        // 2. yol
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")
                        ,"completed",equalTo(false)
                        ,"userId",equalTo(2));

        // Body icerik sorgulamalarini tek body icerisinde kullanirsak soft assert gibi calisir
        // Testlerin bir tanesi fail olsa bile digerlerini sorgulamaya devam eder

        /*
      Note 1: Assetion yaparken Java çalışmayı durdurduğunda hata sonrası kodlar çalışmaz.
              Böylece hata sonrası assertion'lar hakkında bilgi sahibi olamayız.
              Fakat hata öncesi assertion'lar geçmiştir.

     Not 2: Eğer kodumuzu hata noktasında duracak şekilde yazarsak "Hard Assertion" yapmış oluyoruz.

     Not 3: Eğer kodumuzu hata noktasında duramayacak şekilde yazarsak "soft Assertion" yapmış oluyoruz.

     Not 4: Eğer çoklu body() methodu içinde assert yaparsak "Hard Assert",
            tek body() methodu içinde assert yaparsak "Soft Assert"  yapmış oluyoruz.


 */
    }


}
