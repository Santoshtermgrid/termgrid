package com.termgrid.api.appApi.clients.dataroom;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.pojos.dataroom.AddDocumentsIntoDataRoomPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.addFileToDataRoomUrl;

public class AddFileToDataRoomAPI {

    @Step
    public static Response post(AddDocumentsIntoDataRoomPojo addFileToDataRoom){
        return RestResource.post(addFileToDataRoomUrl, addFileToDataRoom);
    }
}
