package com.termgrid.api.appApi.clients.invite;

import com.termgrid.api.appApi.pojos.invite.InviteRequest;
import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.inviteUserToTeamUrl;

public class InviteAPI {

    @Step
    public static Response post(InviteRequest inviteRequest, String username, String password){
        return RestResource.post(inviteUserToTeamUrl, inviteRequest,username,password);
    }
}
