package com.gaggle.base;

import com.gaggle.utillities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class BaseJsonServer {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    public static void setUP() {

        RestAssured.baseURI = ConfigurationReader.getProperty("jsonServeBaseURI");

        RestAssured.basePath = ConfigurationReader.getProperty("jsonServeBasePath");
        requestSpec = new RequestSpecBuilder().build().
                contentType(ContentType.JSON);

    }

}
