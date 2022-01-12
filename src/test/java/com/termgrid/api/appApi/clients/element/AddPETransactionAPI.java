package com.termgrid.api.appApi.clients.element;

import com.termgrid.api.commons.RestResource;
import com.termgrid.api.appApi.pojo.element.AddPETransactionRequest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.*;

public class AddPETransactionAPI {

    @Step
    public static Response post(AddPETransactionRequest addPETransaction, String username, String password){
        return RestResource.post(addPETransactionUrl, addPETransaction,username,password);
    }
    public static Response post(String token, AddPETransactionRequest addPETransaction){
        return RestResource.post(token,addPETransactionUrl, addPETransaction);
    }


}
