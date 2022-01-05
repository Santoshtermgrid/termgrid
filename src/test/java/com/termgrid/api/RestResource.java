package com.termgrid.api;

import com.termgrid.api.appApi.utils.DataLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.termgrid.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, Object body){
        return given(getRequestSpec()).
                body(body).
                auth().preemptive().
                basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
                contentType(ContentType.JSON).
        when().post(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }
    public static Response post(String token,String path, Object body){
        return given(getRequestSpec()).
                body(body).headers("Authorization",token).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }


    public static Response deletebyPathParam(String path, Object body, Map<String, String> pathParams){
        return given(getRequestSpec()).
                body(body).
                pathParams(pathParams).
                auth().preemptive().basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
                when().delete(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }


    public static Response get(String path){
        return given(getRequestSpec()).
                auth().basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
        when().get(path).
        then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response getbyPathParams(String path,HashMap<String,String> pathParams){
        return given(getRequestSpec()).
                pathParams(pathParams).
                auth().preemptive().basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response getbyPathParams(String path,HashMap<String,String> pathParams,String token){
        return given(getRequestSpec()).headers("Authorization",token)
                .when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }


}
