package com.cybertek.day4;

import com.cybertek.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        //get the second counrty name with json path
        //to use jsonpath we assign response to jason path

        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.get("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //to get all countyr ids
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println("allCountryIds = " + allCountryIds);

        //get all country names where their region id is equl to 2
        List<String> countryNames = jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countryNames = " + countryNames);

    }

    @Test
    public void test2(){
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as it_prog
        List<String> mailList = jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println("mailList = " + mailList);

        //get me first name of employees who is making more than 10000
        List<String> namesList = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println("namesList = " + namesList);

        //get the max salary first_name
        String kingFirstName = jsonPath.get("items.max{it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);



    }
}
