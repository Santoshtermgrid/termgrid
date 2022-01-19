package com.termgrid.api.appApi.clients.commons.restassured;

import com.termgrid.api.appApi.clients.commons.utils.DataLoader;
import com.termgrid.api.appApi.clients.commons.utils.TermGridConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.termgrid.api.appApi.clients.commons.restassured.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {



    public static Response post(String path, Object body){
        return RestAssured.given(getRequestSpec()).
                body(body).
                /*auth().preemptive().
                basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).*/
                contentType(ContentType.JSON).
        when().post(path).
        then().spec(getResponseSpec()).
                        statusCode(200).
                extract().
                response();
    }

    public static Response post(String path, Object body,String username,String password){
        return RestAssured.given(getRequestSpec()).
                body(body).
                auth().preemptive().
                basic(username,password).
                        contentType(ContentType.JSON).
                        when().post(path).
                        then().spec(getResponseSpec()).
                statusCode(200).
                        extract().
                        response();
    }
    public static Response post(String token,String path, Object body){
        return RestAssured.given(getRequestSpec()).
                body(body).headers("Authorization",token).
                when().post(path).
                then().spec(getResponseSpec()).
                statusCode(200).
                extract().
                response();
    }

    public static Response post(String path, Object body,Map<String, String> pathParams){
        return RestAssured.given(getRequestSpec()).
                body(body).
                pathParams(pathParams).
                auth().preemptive().basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
                contentType(ContentType.JSON).
                when().post(path).
                then().spec(getResponseSpec()).
                statusCode(200).
                extract().
                response();
    }

    public static Response deletebyPathParam(String path, Object body, Map<String, String> pathParams){
        return RestAssured.given(getRequestSpec()).
                body(body).
                pathParams(pathParams).
                auth().preemptive().basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
                when().delete(path).
                then().spec(getResponseSpec()).
                statusCode(200).
                extract().
                response();
    }


    public static Response get(String path){
        return RestAssured.given(getRequestSpec()).
                auth().basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
        when().get(path).
        then().spec(getResponseSpec()).
                statusCode(200).
                extract().
                response();
    }

    public static Response get(String path,String username,String password){
        return RestAssured.given(getRequestSpec()).
                auth().preemptive().
                basic(username,password).
                contentType(ContentType.JSON).
                when().get(path).
                then().spec(getResponseSpec()).
                statusCode(200).
                extract().
                response();
    }

    public static Response getbyPathParams(String path,HashMap<String,String> pathParams){
        return RestAssured.given(getRequestSpec()).
                pathParams(pathParams).
                auth().preemptive().basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
                when().get(path).
                then().spec(getResponseSpec()).
                statusCode(200).
                extract().
                response();
    }
    public static Response adminGetbyPathParams(String path,HashMap<String,String> pathParams){
        return RestAssured.given(getRequestSpec()).
                pathParams(pathParams).
                auth().preemptive().basic( TermGridConstants.admin,TermGridConstants.adminpassword).
                when().get(path).
                then().spec(getResponseSpec()).
                statusCode(200).
                extract().
                response();
    }

    public static Response getbyPathParams(String path,HashMap<String,String> pathParams,String token){
        return RestAssured.given(getRequestSpec()).headers("Authorization",token)
                .when().get(path).
                then().spec(getResponseSpec()).
                        statusCode(200).
                extract().
                response();
    }


}
