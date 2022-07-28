package com.cybertek.day5;

import com.cybertek.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {

    @DisplayName("get one spartan and deserialize to map")
    @Test
    public void oneSpartanToMap(){

      Response response = given().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

      //body() olmasa da aynı sonucu veriyor
        Map<String, Object> jsonMap = response.body().as(Map.class);

        System.out.println("jsonMap = " + jsonMap);

        String actualName = (String) jsonMap.get("name");
        assertThat(actualName, is("Meta"));




    }

    @DisplayName("GET all spartans to java data structure")
    @Test
    public void getAllSpartan(){

        Response response = get("/api/spartans").
                then().statusCode(200).extract().response();

        //we need convert json to java which is deserialize
        List<Map<String, Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(0).get(\"name\") = " + jsonList.get(0).get("name"));

        Map<String, Object> spartan3 = jsonList.get(2);
        System.out.println("spartan3 = " + spartan3);


    }




}
