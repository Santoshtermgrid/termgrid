package com.termgrid.api.appApi.clients.assignlendertotransaction;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.pojos.assignlendertotransaction.CreateLenderPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.createLenderUnderCompanyUrl;

public class CreateCompanyByLenderAPI {
    @Step
    public static Response post(CreateLenderPojo addLender){
        return RestResource.post(createLenderUnderCompanyUrl, addLender);
    }

   /* @Step
    public static Response post(CreateLenderPojo addLender, HashMap<String, String> pathParam){
        return RestResource.post(createLenderUnderCompanyUrl, addLender, pathParam);
    }*/

    @Step
    public static Response post(CreateLenderPojo addLender, String username, String password){
        return RestResource.post(createLenderUnderCompanyUrl, addLender,username,password);
    }
}
