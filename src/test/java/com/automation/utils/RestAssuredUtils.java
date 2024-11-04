package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {

    static RequestSpecification reqSpecification = RestAssured.given();
    static Response response;
    static String endpoint;

    public static void setEndPoint(String endPoint){
        RestAssuredUtils.endpoint = endPoint;
    }
    public static void setHeader(String key,String value){
        reqSpecification.header(key,value);
    }

    public static void setBody(String filePath){
        try{
            reqSpecification.body(getDataFromJsonFile(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void get(){
        reqSpecification.log().all();
        response=reqSpecification.get(endpoint);
    }

    public static void post(){
        reqSpecification.log().all();
        response=reqSpecification.post(endpoint);
        response.then().log().all();

    }

    public static void put(){
        reqSpecification.log().all();
        response=reqSpecification.put(endpoint);
        response.then().log().all();
    }

    public static void delete(){
        response=reqSpecification.delete(endpoint);
        response.then().log().all();
    }

    public static int getStatusCode(){
        reqSpecification.log().all();
        return response.getStatusCode();
    }

    public static String getDataFromJsonFile(String filename) throws FileNotFoundException {
        String jsonFolderPath = "src/test/resources/data/";
        Scanner sc = new Scanner(new FileInputStream(jsonFolderPath + filename));
        String content = sc.useDelimiter("\\Z").next();
        return content;
    }

}
