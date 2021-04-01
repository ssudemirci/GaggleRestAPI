package com.gaggle.step_definitions;

import com.gaggle.base.BaseDockerImage;
import com.gaggle.utillities.ConfigurationReader;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DockerImageStep extends BaseDockerImage {
    private Response response;
    private JsonPath jsonPath;


//    @BeforeStep
//    public  void init() {
//        setUP();
//    }


    @Given("Users Performs Get operation")
    public void users_performs_get_operation() {
        response = given().
                contentType(ContentType.JSON).log().all().
                when().get("http://localhost:8085/Get").prettyPeek();
    }

    @Then("User should see the success status  as {int}")
    public void userShouldSeeTheSuccessStatusAs(int statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode);

    }
    @Then("Docker Payload {string} value is {string}")
    public void docker_payload_value_is(String body, String value) {
        jsonPath=response.jsonPath();

        Assert.assertEquals(jsonPath.getString(body),value);


    }
}