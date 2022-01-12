package com.termgrid.api.appApi.clients.admin;

import com.termgrid.api.appApi.pojo.admin.JoinTeamLogin;
import com.termgrid.api.appApi.pojo.company.AddCompanyRequest;
import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.;

public class JoinTeamLoginAPI {


    @Step
    public static Response post(String url ,JoinTeamLogin joinTeamLogin, String username, String password){
        return RestResource.post(url, joinTeamLogin,username,password);
    }
}
