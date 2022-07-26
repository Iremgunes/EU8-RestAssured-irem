package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeTestCase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://52.87.170.214:8000";
    }

    /*TASK
        Given Accept type application/xml
        When user send GET request to /api/spartans/10 end point
        Then status code must be 406
        And response Content Type must be application/xml;charset=UTF-8
        */


    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.XML).
                when().get("/api/spartans/10");

        assertEquals(406, response.statusCode());

        assertEquals("application/xml;charset=UTF-8", response.contentType());


    }

    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

    @DisplayName("GET request /api/spartans/{id} Negative Test")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 500).
                when().get("/api/spartans/{id}");

        assertEquals(404, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Not Found"));

    }
/*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON).
                and().queryParam("gender", "female").
                and().queryParam("NameContains", "e").
                when().get("/api/spartans/search");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){

        //create a map and add query parameters
        Map<String, Object> queryMap = new HashMap<>();

        queryMap.put("nameContains", "e");
        queryMap.put("gender", "Female");

        Response response = given().log().all().
                accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when()
                .get("/api/spartans/search");

        //verify status code 200
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify NotFound in the json payload/body

        //"Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        //"Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));



    }




}
