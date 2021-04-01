package com.gaggle.base;

import com.gaggle.utillities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class BaseJsonServer {
    public  RequestSpecification jsonRequestSpec;

    public  void setUP() {
        RestAssured.baseURI = ConfigurationReader.getProperty("jsonServeBaseURI");
        RestAssured.basePath = ConfigurationReader.getProperty("jsonServeBasePath");
        jsonRequestSpec = new RequestSpecBuilder().build().
                contentType(ContentType.JSON);

    }

}
