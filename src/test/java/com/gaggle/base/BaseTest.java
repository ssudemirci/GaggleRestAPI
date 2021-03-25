package com.gaggle.base;

import com.gaggle.utillities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class BaseTest {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public static void setUP() {

        RestAssured.baseURI = ConfigurationReader.getProperty("baseURI");
        RestAssured.port = 3000;
        RestAssured.basePath = ConfigurationReader.getProperty("basePath");
        requestSpec = new RequestSpecBuilder().build().
                contentType(ContentType.JSON);

    }

    public static void main(String[] args) {

    }
}
