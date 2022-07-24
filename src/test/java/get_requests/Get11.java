package get_requests;

import base_urls.Gorest_BaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertTrue;

public class Get11 extends Gorest_BaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Nalini Acharya", "Anilaabh Mehra", "Chandravati Rana" are among the users
        And
            The female users are more than male users
     */

    @Test
    public void get01() {
        // 1 - Set the Url
        spec.pathParam("first","users");

        // 2 - Set the expected data

        // 3 - Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4 - Do assertion



        response.then().assertThat().statusCode(200)
                        .body("meta.pagination.limit",equalTo(10)
                        ,"meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1")
                        ,"data.id",hasSize(10),"data.status",hasItem("active")
                        /*,"data.name",hasItems("Nalini Acharya", "Anilaabh Mehra", "Chandravati Rana")*/);


        //1. Yol: Tum cinsiyetleri alip karsilastiririz
        JsonPath json=response.jsonPath();
        List<String> genders=json.getList("data.gender");
        System.out.println(genders);
        int numOfFemales=0;

        for (String each:genders
             ) {
            if (each.equalsIgnoreCase("Female")){
                numOfFemales++;
            }
        }
        int numOfMales=genders.size()-numOfFemales;

        assertTrue(numOfFemales>numOfMales);
        System.out.println("numofFemale : "+numOfFemales);
        System.out.println("numofMale : "+numOfMales);

        // 2. Yol: Tum Bayan ve Baylari ayri ayri Groovy ile cekelim

        List<String> femaleList=json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList=json.getList("data.findAll{it.gender=='male'}.gender");
        assertTrue(femaleList.size()>maleList.size());

    }
}
