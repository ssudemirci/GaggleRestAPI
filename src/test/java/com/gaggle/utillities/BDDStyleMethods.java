package com.gaggle.utillities;

import com.gaggle.pojo.Fields;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class BDDStyleMethods {
    private static Response response;
    public static RequestSpecification requestSpec;
    static JsonPath jsonPath;
    private static String id;


    public static void simpleGetPost(String postNumber) {


        given().contentType(ContentType.JSON).pathParam("id", postNumber).log().all()
                .when().get("http://localhost/3000/payload/{id}").prettyPeek().
                then().statusCode(200).body("message",is("postman "));
    }



    public static RequestSpecification requestSpecification(String headerKey,String headerValue) {
        RestAssured.baseURI = ConfigurationReader.getProperty("baseUrl");
        requestSpec = new RequestSpecBuilder().build().
                contentType(ContentType.JSON).
                pathParam(headerKey, headerValue);
        return requestSpec;
    }




    public static void setRequest(String method,RequestSpecification requestSpec,String post_iSO_currentTime,String
            post_message ,String idPost){
        id=idPost;

        switch (method) {
            case "Post":
                post_iSO_currentTime = Utils_Gaggle.iSO_CurrentTime();
                post_message = "Welcome to the machine.";
                Fields body = new Fields(post_message, post_iSO_currentTime);
                response = given().
                        spec(requestSpec).body(body).log().all().
                        when().
                        post().prettyPeek();
                jsonPath = response.jsonPath();
                id = response.jsonPath().getString("id");

                break;
            case "Get":
                requestSpec = requestSpec.pathParam("id", id);
                response =
                        given().spec(requestSpec).
                                when().
                                get("/{id}").prettyPeek();
                break;
            case "Put":
                String update = "update ";
                body = new Fields(update + post_message, post_iSO_currentTime);
                response = given().
                        spec(requestSpec).body(body).log().all().
                        when().
                        put("/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                break;
            case "Patch":
                String partially = "partially ";
                body = new Fields(partially + post_message, post_iSO_currentTime);
                response = given().
                        spec(requestSpec).body(body).log().all().
                        when().
                        patch("/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                break;
            case "Delete":
                response = given().
                        spec(requestSpec).log().all().
                        when().
                        delete("/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                break;
        }

    }




}




