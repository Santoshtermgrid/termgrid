package com.termgrid.api.commons;

import com.termgrid.api.commons.utils.DataLoader;
import com.termgrid.api.commons.utils.TermGridConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static com.termgrid.api.commons.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, Object body){
        return given(getRequestSpec()).
                body(body).
                /*auth().preemptive().
                basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).*/
                        contentType(ContentType.JSON).
                        when().post(path).
                        then().spec(getResponseSpec()).
                        extract().
                        response();
    }

    public static Response post(String path, Object body,String username,String password){
        return given(getRequestSpec()).
                body(body).
                auth().preemptive().
                basic(username,password).
                contentType(ContentType.JSON).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String path, Object body, Map<String, String> pathParams){
        return given(getRequestSpec()).
                body(body).pathParams(pathParams).
                auth().preemptive().
                basic(DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword()).
                contentType(ContentType.JSON).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response post(String path, Object body, String queryParams){
        return given(getRequestSpec()).
                body(body).queryParam(queryParams).
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

    public static Response get(String path,String username,String password){
        return given(getRequestSpec()).
                auth().preemptive().
                basic(username,password).
                contentType(ContentType.JSON).
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
    public static Response adminGetbyPathParams(String path,HashMap<String,String> pathParams){
        return given(getRequestSpec()).
                pathParams(pathParams).
                auth().preemptive().basic( TermGridConstants.admin,TermGridConstants.adminpassword).
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
