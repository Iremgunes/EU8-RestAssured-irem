package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://52.87.170.214:1000/ords/hr";
    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1(){

        Response response = RestAssured.get("/regions");

        System.out.println(response.statusCode());

    }

/*
    Given accept type is application/json
    When user sends get request to /regions/2
    Then response status code must be 200
    and content type equals to application/json
    and response body contains   Americas

 */

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).
                when().get("/regions/2");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Americas"));


        Response response1 = RestAssured.get("/region/2");

        //System.out.println(response1.body().asString());

    }



}
