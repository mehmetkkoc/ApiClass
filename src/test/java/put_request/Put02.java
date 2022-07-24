package put_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/update/21
        {
          "employee_name": "Tom Hanks",
          "employee_salary": 111111,
          "employee_age": 23,
          "profile_image": "Perfect image"
          }
     When
        User sends the PUT request
     Then
        Status code is 200
     And
        Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }   */

    @Test
    public void put01() {
        spec.pathParams("first","update","second",21);
        DummyDataPojo dataPojo=new DummyDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyResponseBodyPojo expectedDataPojo=new DummyResponseBodyPojo("success",dataPojo,"Successfully! Record has been updated.");
        Response response=given().spec(spec).contentType(ContentType.JSON).body(dataPojo).when().put("/{first}");
        DummyResponseBodyPojo actualData=JsonUtil.convertJsonToJavaObject(response.asString(),DummyResponseBodyPojo.class);
    }
}
