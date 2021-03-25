package com.gaggle.step_definitions;

import com.gaggle.pojo.Fields;
import com.gaggle.utillities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    public static Fields bodyForPost(String message, String timestamp){
        Fields body=new Fields(message,timestamp);
        return body;


    }

    public static String iSO_CurrentTime(){
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        String iSO8601_time = sdf.format(date);


      return iSO8601_time;
    }

    public static void setRequest(String method,RequestSpecification requestSpec,String post_iSO_currentTime,String
            post_message ,String idPost){
        id=idPost;

        switch (method) {
            case "Post":
                post_iSO_currentTime = BDDStyleMethods.iSO_CurrentTime();
                post_message = "Welcome to the machine.";
                Fields body = new Fields(post_message, post_iSO_currentTime);
                response = given().
                        spec(requestSpec).body(body).log().all().
                        when().
                        post().prettyPeek();
                jsonPath = response.jsonPath();
                id = response.jsonPath().getString("id");
                //assertThat(response.statusCode(), is(201));
                // assertThat(jsonPath.getString("message"), is(post_message));
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
                //  Assert.assertTrue(jsonPath.getString("message").contains(update));
                break;
            case "Patch":
                String partially = "partially ";
                body = new Fields(partially + post_message, post_iSO_currentTime);
                response = given().
                        spec(requestSpec).body(body).log().all().
                        when().
                        patch("/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                //  Assert.assertTrue(jsonPath.getString("message").contains(partially));
                break;
            case "Delete":
                response = given().
                        spec(requestSpec).log().all().
                        when().
                        delete("/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                // Assert.assertTrue(response.getBody().toString().isEmpty());
                break;
        }

    }




}




