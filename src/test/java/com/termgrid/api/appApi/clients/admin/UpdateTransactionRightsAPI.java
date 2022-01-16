package com.termgrid.api.appApi.clients.admin;

import com.termgrid.api.appApi.pojos.admin.UpdateTransactionRequest;
import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import io.qameta.allure.Step;
import static com.termgrid.api.appApi.endpoints.ApiEndPoints.updateTransactionUrl;

public class UpdateTransactionRightsAPI {

    @Step
    public static String post(UpdateTransactionRequest updateTransactionRequest, String username, String password){
        return RestResource.post(updateTransactionUrl, updateTransactionRequest,username,password).getBody().asString();
    }
}


