package com.gaggle.step_definitions;

import com.gaggle.base.BaseJsonServer;
import com.gaggle.pojo.Fields;
import com.gaggle.utillities.ConfigurationReader;
import com.gaggle.utillities.Utils_Gaggle;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class JsonServerStep extends BaseJsonServer {
    private  static String post_iSO_currentTime;
    private  static String post_message;
    private static String id;
    private  Response response;
    private  JsonPath jsonPath;
    //private  String baseURL = ConfigurationReader.getProperty("jsonServeBaseURI") +
          //  ConfigurationReader.getProperty("jsonServeBasePath");

//    @BeforeStep
//    private  void init() {
//       setUP();
//
//    }

    @Given("Users Performs {string} operation")
    public void users_performs_operation(String method) {
        String baseURL = ConfigurationReader.getProperty("");

        switch (method) {
            case "Post":
                post_iSO_currentTime = Utils_Gaggle.iSO_CurrentTime();
                post_message = "Welcome to the machine.";
                Fields body = new Fields(post_message, post_iSO_currentTime);
                response = given().contentType(ContentType.JSON).
                        body(body).log().all().
                        when().
                        post("http://localhost:3000/payload").prettyPeek();
                jsonPath = response.jsonPath();
                id = response.jsonPath().getString("id");
                break;
            case "Get":
                response =
                        given().contentType(ContentType.JSON).
                                when().
                                get("http://localhost:3000/payload/{id}", id).prettyPeek();
                break;
            case "Put":
                String update = "update ";
                body = new Fields(update + post_message, post_iSO_currentTime);
                response = given().
                      contentType(ContentType.JSON).body(body).log().all().
                        when().
                        put("http://localhost:3000/payload/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                break;
            case "Patch":
                String partially = "partially ";
                body = new Fields(partially + post_message, post_iSO_currentTime);
                response = given().
                     contentType(ContentType.JSON).body(body).log().all().
                        when().
                        patch("http://localhost:3000/payload/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                break;
            case "Delete":
                response = given().
                       contentType(ContentType.JSON).log().all().
                        when().
                        delete("http://localhost:3000/payload/" + id).prettyPeek();
                jsonPath = response.jsonPath();
                break;
        }
    }

    @Then("User should see the success status {string}")
    public void user_should_see_the_success_status(String string) {
        Assert.assertEquals(response.statusCode(), Integer.parseInt(string));

    }

    @Then("Payload {string} value is {string}")
    public void payload_value_is(String body, String value) {
        jsonPath = response.jsonPath();
        String actualValue = jsonPath.getString(body);

        if (actualValue.contains(value)) {
            Assert.assertTrue(actualValue.contains(value));
        } else {
            Assert.assertEquals("Test failed!!" + jsonPath.getString(body), value);
        }

    }



}
