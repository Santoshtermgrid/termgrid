package com.termgrid.api.appApi.clients.termsheet;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.pojos.termsheet.AddTermSheetRequestPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.createTermSheetUrl;

public class CreateTermSheetAPI {

    @Step
    public static Response post(AddTermSheetRequestPojo addTermSheet){
        return RestResource.post(createTermSheetUrl, addTermSheet);
    }

    @Step
    public static Response post(AddTermSheetRequestPojo addTermSheet, String username, String password){
        return RestResource.post(createTermSheetUrl, addTermSheet,username,password);
    }
    public static Response post(AddTermSheetRequestPojo addTermSheet,  Map<String, String> pathParams){
        return RestResource.post(createTermSheetUrl, addTermSheet,pathParams);
    }
}


