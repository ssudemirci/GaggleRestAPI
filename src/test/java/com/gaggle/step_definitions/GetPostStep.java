package com.gaggle.step_definitions;

import com.gaggle.base.BaseTest;
import com.gaggle.pojo.Fields;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class GetPostStep extends BaseTest {
    private String post_iSO_currentTime = BDDStyleMethods.iSO_CurrentTime();
    private static String post_message;
    private static String id;
    private Response response;
    private JsonPath jsonPath;

    @BeforeStep
    public static void init() {
        setUP();

    }
    @Given("Users Performs {string} operation")
    public void users_performs_operation(String method) {

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
        //BDDStyleMethods.setRequest(method,requestSpec,BDDStyleMethods.iSO_CurrentTime(),"welcome",id);

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
            Assert.assertEquals("Test failed!!" + jsonPath.get(body), value);
        }

    }

}
