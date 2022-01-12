package com.termgrid.api.appApi.clients.users;

import com.termgrid.api.appApi.pojo.users.RegisterResponse;
import com.termgrid.api.commons.RestResource;
import com.termgrid.api.appApi.pojo.users.RegisterRequest;
import io.qameta.allure.Step;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.registerLoginUrl;

public class RegisterLoginAPI {

    @Step
    public static RegisterResponse post(RegisterRequest register){
        return RestResource.post(registerLoginUrl, register).getBody().as(RegisterResponse.class);
    }



}
