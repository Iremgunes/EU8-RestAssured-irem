package com.cybertek.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class SpartanTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://52.87.170.214:8000";
    }

}
