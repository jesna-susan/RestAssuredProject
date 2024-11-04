package com.automation.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredDemoPut {
    public static void main(String[] args) throws FileNotFoundException {

        RestAssured.baseURI="https://restful-booker.herokuapp.com";

        RequestSpecification reqSpecification = RestAssured.given();

        reqSpecification.contentType("application/json");
        reqSpecification.accept("application/json");

        //code to create a token

        RequestSpecification authRequest = RestAssured.given();
        authRequest.contentType("application/json");
        authRequest.body("{\"username\":\"admin\", \"password\":\"password123\"}");
        Response authResponse = authRequest.post("/auth");
        String token = authResponse.jsonPath().getString("token");
        System.out.println(token);

        reqSpecification.cookie("token",token);

        Scanner sc = new Scanner(new FileInputStream("src/test/resources/data/update_booking.json"));
        String content = sc.useDelimiter("\\Z").next();
        reqSpecification.body(content);

        reqSpecification.log().all();

        Response response = reqSpecification.put("booking/4724");
        response.then().log().all().assertThat().statusCode(200);
        System.out.println(response.statusCode());

    }
}
