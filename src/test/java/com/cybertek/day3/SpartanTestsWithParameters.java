package com.cybertek.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://52.87.170.214:8000";
    }


/*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */

    @DisplayName("GET request to /api/spartans/{id} with id")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 5).
                when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());

        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("Blythe"));



    }



}
