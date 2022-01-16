package com.termgrid.api.appApi.clients.users;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.verfiyEmailUrl;

public class VerifyEmailAPI {

    @Step
    public static Response get(HashMap<String,String> pathParams){
        return RestResource.adminGetbyPathParams(verfiyEmailUrl, pathParams);
    }
}
