package com.automation.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredDemoGet {
    public static void main(String[] args) {
        RestAssured.baseURI="https://restful-booker.herokuapp.com";
        RequestSpecification reqSpecification = RestAssured.given();
        Response response = reqSpecification.when().get("booking/4508");
        response.then().log().all().assertThat().statusCode(200);
        System.out.println("Status code:"+ response.statusCode());

    }
}
