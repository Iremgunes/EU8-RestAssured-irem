package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with path method")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).
                queryParam("q","{\"region_id\":2}" )
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hashMore\") = " + response.path("hashMore"));

        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        String secondCountryId = response.path("items[1].country_id");
        System.out.println("secondCountryId = " + secondCountryId);

        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");

        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> allRegionIds = response.path("items.region_id");

        for (Integer regionId : allRegionIds) {
            System.out.println("Region Id = " + regionId);
            assertEquals(2, regionId);
        }


    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG",jobID);
        }

        //HW
        //print name of each IT_PROGs

        List<String> namesAlll = response.path("items.first_name");
        System.out.println("namesAlll = " + namesAlll);
    }




}
