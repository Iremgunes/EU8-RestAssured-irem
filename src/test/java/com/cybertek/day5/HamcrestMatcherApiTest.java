package com.cybertek.day5;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherApiTest {
    @DisplayName("one spartan with hamcrest and chaining")
    @Test
    public void test1(){

        /*
        Given accept type is json
        And path param id is 15
        When user sends a get request to "api/spartans/{id}"
        Then status code is 200
        And content-type is "application/json"
        And response payload values match the following:
            id": 15,
    "name": "Meta",
    "gender": "Female",
    "phone": 1938695106
      */
        given().accept(ContentType.JSON).
                and().pathParam("id", 15).
                when().get("http://52.87.170.214:8000/api/spartans/{id}")
                .then()
                    .statusCode(200)
                    .and().assertThat()
                .contentType("application/json")
                .and()
                .body("id", equalTo(15),
                        "name", is("Meta"),
                        "gender", is("Female"),
                        "phone", is(1938695106));


    }

    @Test
    public void teacherData(){

       given().
                accept(ContentType.JSON).
                and().
                pathParam("id", 20762).
        when()
                . get("http://api.cybertektraining.com/teacher/{id}").
        then().
                statusCode(200).
                and().
                contentType("application/json;charset=UTF-8").
                and().
                header("Content-Length", is("245")).
                and().
                header("Date", notNullValue()).
               and().assertThat().
               body("teachers[0].firstName", is("Steve")).
               body("teachers[0].lastName", is("Jobs")).
                body("teachers[0].gender", equalTo("male"));  ;


    }

    @Test
    public void teacherTest(){
        given().
                accept(ContentType.JSON).
        when()
                . get("http://api.cybertektraining.com/teacher/all").
        then().
                statusCode(200).
                and().
                body("teachers.firstName", hasItems("Steve", "Leoneli", "Andrii"));

    }





}
