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
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DockerImageStep {
    private Response response;
    private JsonPath jsonPath;
    public static RequestSpecification requestSpec;


    @BeforeStep
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("dockerBaseURI");
        RestAssured.basePath = ConfigurationReader.getProperty("dockerBasePath");
    }


    @Given("Users Performs Get operation")
    public void users_performs_get_operation() {
        requestSpec = new RequestSpecBuilder().log(LogDetail.ALL).build();
        response = given().
                spec(requestSpec).log().all().
                when().get("").prettyPeek();
    }

    @Then("User should see the success status  as {int}")
    public void userShouldSeeTheSuccessStatusAs(int statusCode) {
        Assert.assertEquals(response.statusCode(),statusCode);

    }

    @And("Payload has {string}")
    public void payload_has(String value) {
        Assert.assertTrue(response.body().prettyPrint().contains(value));
    }

}
