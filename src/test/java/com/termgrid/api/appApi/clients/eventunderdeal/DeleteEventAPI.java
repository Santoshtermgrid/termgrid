package com.termgrid.api.appApi.clients.eventunderdeal;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.pojos.eventunderdeal.DeleteEventRequestPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.deletedCreatedEventUrl;

public class DeleteEventAPI {

    @Step
    public static Response post(DeleteEventRequestPojo deleteEventRequestPojo, String username, String password){
        return RestResource.post(deletedCreatedEventUrl, deleteEventRequestPojo,username,password);
    }
    public static Response post(String token, DeleteEventRequestPojo deleteEventRequestPojo){
        return RestResource.post(token,deletedCreatedEventUrl, deleteEventRequestPojo);
    }
}