package com.gaggle.base;

import com.gaggle.utillities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class BaseDockerImage {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public static void setUP() {

        RestAssured.baseURI = ConfigurationReader.getProperty("dockerBaseURI");

        RestAssured.basePath = ConfigurationReader.getProperty("dockerBasePath");
        requestSpec = new RequestSpecBuilder().build();

    }

}
