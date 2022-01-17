package com.termgrid.api.appApi.element;



import com.termgrid.api.RestResource;
import com.termgrid.api.appApi.pojo.addFileToDataRoom.AddDocumentsIntoDataRoomPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static com.termgrid.api.ApiEndPoints.addFileToDataRoomUrl;

public class AddFileToDataRoomAPI {

    @Step
    public static Response post(AddDocumentsIntoDataRoomPojo addFileToDataRoom){
        return RestResource.post(addFileToDataRoomUrl, addFileToDataRoom);
    }
}
