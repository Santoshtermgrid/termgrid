package com.termgrid.api.appApi.clients.termsheet;

import com.termgrid.api.appApi.pojo.addTermSheet.AddTermSheetPojo;
import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;
import static com.termgrid.api.appApi.endpoints.ApiEndPoints.*;

public class CreateWebFormConfigAPI {

    @Step
    public static Response post(AddTermSheetPojo addTermSheet){
        return RestResource.post(createTermSheetUrl, addTermSheet);
    }

    public static Response post(AddTermSheetPojo addTermSheet, String username, String password){
        return RestResource.post(createTermSheetUrl, addTermSheet,username,password);
    }

    public static Response post(AddTermSheetPojo addTermSheet,  Map<String, String> pathParams){
        return RestResource.post(createTermSheetUrl, addTermSheet,pathParams);
    }
}


