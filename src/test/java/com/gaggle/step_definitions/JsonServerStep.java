package com.gaggle.step_definitions;

import com.gaggle.base.BaseJsonServer;
import com.gaggle.pojo.Fields;
import com.gaggle.utillities.Utils_Gaggle;
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

public class JsonServerStep extends BaseJsonServer {
    private String post_iSO_currentTime;
    private static String post_message;
    private static String id;
    private Response response;
    private JsonPath jsonPath;

    @BeforeStep
    private static void init() {
        setUP();

    }
    @Given("Users Performs {string} operation")
    public void users_performs_operation(String method) {

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

    @Then("User should see the success status {string}")
    public void user_should_see_the_success_status(String string) {
        Assert.assertEquals(response.statusCode(), is(Integer.parseInt(string)));

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
