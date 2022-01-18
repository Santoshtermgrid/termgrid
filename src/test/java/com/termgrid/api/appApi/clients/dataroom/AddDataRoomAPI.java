package com.termgrid.api.appApi.clients.dataroom;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.pojos.dataroom.AddDataRoomPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.addFolderElementUrl;

public class AddDataRoomAPI {
    @Step
    public static Response post(AddDataRoomPojo addDataRoom){
        return RestResource.post(addFolderElementUrl, addDataRoom);
    }

    public static Response post(AddDataRoomPojo addDataRoom, String username, String password){
        return RestResource.post(addFolderElementUrl, addDataRoom,username,password);
    }
}
