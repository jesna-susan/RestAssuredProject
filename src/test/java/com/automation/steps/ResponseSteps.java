package com.automation.steps;

import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import io.restassured.response.Response;

public class ResponseSteps {
    @Then("verify status code is {int}")
    public void verifyStatusCodeIs(int statusCode) {
       Assert.assertEquals(RestAssuredUtils.getStatusCode(), statusCode) ;
    }
}
