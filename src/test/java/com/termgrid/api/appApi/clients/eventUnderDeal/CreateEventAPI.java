package com.termgrid.api.appApi.clients.eventUnderDeal;

import com.termgrid.api.appApi.pojo.createEvent.CreateEventRequestPojo;
import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.createEventUrl;

public class CreateEventUnderDealTeamAPI {
    @Step
    public static Response post(CreateEventRequestPojo createEventRequest, String username, String password){
        return RestResource.post(createEventUrl, createEventRequest,username,password);
    }
    public static Response post(String token, CreateEventRequestPojo createEventRequest){
        return RestResource.post(token,createEventUrl, createEventRequest);
    }
}
