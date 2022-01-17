package com.termgrid.api.appApi.clients.user;

import com.termgrid.api.commons.RestResource;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.getUserUrl;

public class GetUserAPI {

    public static Response get(String username, String password){
        return RestResource.get(getUserUrl, username,password);
    }
}
