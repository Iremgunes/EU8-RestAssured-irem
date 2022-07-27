package com.cybertek.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class HRTestBase {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://52.87.170.214:1000/ords/hr";
    }

}
