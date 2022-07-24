package post_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyDataPojo;
import pojos.DummyResponseBodyPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends DummyRestApiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
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
                            "profile_image": "Perfect image",
                            "id": 8290
                        },
                        "message": "Successfully! Record has been added."
                    }     */
    /*
    Given
        https://dummy.restapiexample.com/api/v1/create
        {
         "employee_name": "Tom Hanks",
         "employee_salary": 111111,
         "employee_age": 23,
         "profile_image": "Perfect image"
         }
     When
        User sends the POST request
     Then
        Status code is 200
     And
        {
          "status": "success",
          "data": {
              "employee_name": "Tom Hanks",
              "employee_salary": 111111,
              "employee_age": 23,
              "profile_image": "Perfect image",
              "id": 8290
          },
          "message": "Successfully! Record has been added."
                    }
     */
    @Test
    public void post01() {
        spec.pathParam("first","create");
        DummyDataPojo dummyDataPojo = new DummyDataPojo("Tom Hanks",111111,23,"Perfect image");
        DummyResponseBodyPojo dummyResponseBodyPojo = new DummyResponseBodyPojo("success",dummyDataPojo,"Successfully! Record has been added.");
        Response response =given().spec(spec).contentType(ContentType.JSON).body(dummyDataPojo).when().post("/{first}");
        DummyResponseBodyPojo actualData= JsonUtil.convertJsonToJavaObject(response.asString(),DummyResponseBodyPojo.class);
        assertEquals(dummyResponseBodyPojo.getStatus(),actualData.getStatus());
        assertEquals(dummyResponseBodyPojo.getData().getEmployee_name(),actualData.getData().getEmployee_name());
    }
}
