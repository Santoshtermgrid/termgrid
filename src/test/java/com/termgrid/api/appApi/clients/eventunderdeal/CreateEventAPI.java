package com.termgrid.api.appApi.clients.eventunderdeal;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.pojos.eventunderdeal.CreateEventRequestPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.createEventUrl;

public class CreateEventAPI {
    @Step
    public static Response post(CreateEventRequestPojo createEventRequest, String username, String password){
        return RestResource.post(createEventUrl, createEventRequest,username,password);
    }
    public static Response post(String token, CreateEventRequestPojo createEventRequest){
        return RestResource.post(token,createEventUrl, createEventRequest);
    }

    public static Response deleteUsingResponse(Response response, String username, String password){
        return RestResource.post(createEventUrl, response,username,password);
    }
}
