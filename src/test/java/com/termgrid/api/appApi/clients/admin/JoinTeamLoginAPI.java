package com.termgrid.api.appApi.clients.admin;

import com.termgrid.api.appApi.pojos.admin.JoinTeamLoginRequest;
import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import io.qameta.allure.Step;

public class JoinTeamLoginAPI {


    @Step
    public static String post(String url , JoinTeamLoginRequest joinTeamLoginRequest, String username, String password){
        return RestResource.post(url, joinTeamLoginRequest,username,password).getBody().asString();
    }
}
