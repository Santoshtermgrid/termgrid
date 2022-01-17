package com.termgrid.api.appApi.clients.addfiletodataroom;


import com.termgrid.api.commons.RestResource;
import io.restassured.response.Response;
import com.termgrid.api.appApi.pojo.addFileToDataRoom.AddDocumentsIntoDataRoomPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.addFileToDataRoomUrl;

public class AddFileToDataRoomAPI {

    @Step
    public static Response post(AddDocumentsIntoDataRoomPojo addFileToDataRoom){
        return RestResource.post(addFileToDataRoomUrl, addFileToDataRoom);
    }
}
