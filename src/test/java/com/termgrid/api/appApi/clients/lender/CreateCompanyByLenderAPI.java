package com.termgrid.api.appApi.clients.lender;

import com.termgrid.api.appApi.pojo.assignlendertotrans.CreateLenderPojo;
import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.createLenderUnderCompanyUrl;

public class CreateCompanyByLenderAPI {
    @Step
    public static Response post(CreateLenderPojo addLender){
        return RestResource.post(createLenderUnderCompanyUrl, addLender);
    }

    public static Response post(CreateLenderPojo addLender, HashMap<String, String> pathParam){
        return RestResource.post(createLenderUnderCompanyUrl, addLender, pathParam);
    }

    public static Response post(CreateLenderPojo addLender, String username, String password){
        return RestResource.post(createLenderUnderCompanyUrl, addLender,username,password);
    }
}
