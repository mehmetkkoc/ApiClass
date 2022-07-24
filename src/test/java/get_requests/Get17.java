package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyRestApiBaseUrl {

    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */
    /*
    Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User send get request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."

    */

    @Test
    public void get01() {
        // 1 - Set the url
        spec.pathParams("first","employee","second",1);
        DummyDataPojo dummyDataPojo =new DummyDataPojo("Tiger Nixon",320800,61,"");
        DummyResponseBodyPojo dummyResponseBodyPojo =new DummyResponseBodyPojo("success", dummyDataPojo,"Successfully! Record has been fetched.");

        // 2 - Set the expected data
        // 3 - Send request and get response
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
        DummyResponseBodyPojo responseBody= JsonUtil.convertJsonToJavaObject(response.asString(), DummyResponseBodyPojo.class);
        System.out.println(responseBody);
        assertEquals(dummyResponseBodyPojo.getStatus(),responseBody.getStatus());
        assertEquals(dummyResponseBodyPojo.getMessage(),responseBody.getMessage());
        assertEquals(dummyResponseBodyPojo.getData().getEmployee_name(),responseBody.getData().getEmployee_name());
        assertEquals(dummyResponseBodyPojo.getData().getEmployee_age(),responseBody.getData().getEmployee_age());
        assertEquals(dummyResponseBodyPojo.getData().getEmployee_salary(),responseBody.getData().getEmployee_salary());


    }
}
