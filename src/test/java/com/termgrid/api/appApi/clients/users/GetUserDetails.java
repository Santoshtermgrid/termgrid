package com.termgrid.api.appApi.clients.users;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.clients.commons.utils.TermGridConstants;
import com.termgrid.api.appApi.pojos.users.GetUserDetailsResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.getUserUrl;

public class GetUserDetails {

    @Step
    public static GetUserDetailsResponse get(String username){
        return RestResource.get(getUserUrl, username, TermGridConstants.password).getBody().as(GetUserDetailsResponse.class);
    }
}
