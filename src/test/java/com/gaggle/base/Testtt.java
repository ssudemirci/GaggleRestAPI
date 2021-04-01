package com.gaggle.base;

import com.gaggle.pojo.Fields;
import com.gaggle.utillities.Utils_Gaggle;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Testtt {
    private String post_iSO_currentTime;
    private static String post_message;
    private static String id;
    private Response response;
    private JsonPath jsonPath;

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
        RestAssured.basePath = "/Get";

    }

    @Test
    public void meee(){
       Response response=given().get();
       jsonPath=response.jsonPath();
        System.out.println(jsonPath.getString("message"));
    }


}
