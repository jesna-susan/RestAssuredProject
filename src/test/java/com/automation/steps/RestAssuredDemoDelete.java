package com.automation.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredDemoDelete {
    public static void main(String[] args) {
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RequestSpecification reqSpecification = RestAssured.given();
        reqSpecification.contentType("application/json");
        //code to create a token

        RequestSpecification authRequest = RestAssured.given();
        authRequest.contentType("application/json");
        authRequest.body("{\"username\":\"admin\", \"password\":\"password123\"}");
        Response authResponse = authRequest.post("/auth");
        String token = authResponse.jsonPath().getString("token");
        System.out.println(token);

        reqSpecification.cookie("token",token);

        Response response = reqSpecification.delete("/booking/2294");
        response.then().log().all().assertThat().statusCode(200);
        System.out.println(response.statusCode());
    }
}
