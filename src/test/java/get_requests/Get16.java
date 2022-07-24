package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;


import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyRestApiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */
    /*
                    Given
                          https://dummy.restapiexample.com/api/v1/employees
                       When
                   I send GET Request to the URL
                 Then
                   Status code is 200
                And
                    There are 24 employees
                 And
                    "Tiger Nixon" and "Garrett Winters" are among the employees
                 And
                     The greatest age is 66
                 And
                     The name of the lowest age is "Tatyana Fitzpatrick"
                 And
                     Total salary of all employees is 6,644,770
     */

    @Test
    public void get01() {
        // 1 - Set the Url
        spec.pathParam("first","employees");

        // 2 - Set the sxpscted data
        // 3 - Send Request and get the response
        Response response=given().spec(spec).get("/{first}");
        // response.prettyPrint();

        // 4 - Do assertion
        response.then().assertThat()
                .statusCode(200)
                .body("data.id",hasSize(24)
                        ,"data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        JsonPath json=response.jsonPath();
        List<Integer> ageList=json.getList("data.findAll{it.id}.employee_age");

        System.out.println(ageList);
        Collections.sort(ageList);
        System.out.println(ageList);
        assertEquals(66,(int)ageList.get(ageList.size()-1));
        String minAgeName=json.getString("data.findAll{it.employee_age=="+ageList.get(0)+"}.employee_name");
        System.out.println(minAgeName);
        assertEquals("[Tatyana Fitzpatrick]",minAgeName);

        // vi) Total salary of all employees is 6,644,770
        List<Integer> salaryList = json.getList("data.findAll{it.id}.employee_salary");
        int sum=0;
        for (int each:salaryList
             ) {
            sum+=each;

        }
        System.out.println(sum);
        assertEquals(6644770,sum);

        System.out.println(salaryList.stream().reduce(0, (t, u) -> t + u));
        System.out.println(salaryList.stream().reduce(0, Math::addExact));

    }
}
