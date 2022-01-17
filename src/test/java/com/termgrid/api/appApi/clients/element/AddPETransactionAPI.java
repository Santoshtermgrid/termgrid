package com.termgrid.api.appApi.clients.element;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.clients.commons.utils.TermGridConstants;
import com.termgrid.api.appApi.pojos.element.AddPETransactionRequest;
import com.termgrid.api.appApi.pojos.element.AddPETransactionResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.*;

public class AddPETransactionAPI {

    @Step
    public static Response post(AddPETransactionRequest addPETransaction, String username, String password){
        return RestResource.post(addPETransactionUrl, addPETransaction,username,password);
    }

    @Step
    public static AddPETransactionResponse post(AddPETransactionRequest addPETransaction, String username){
        return RestResource.post(addPETransactionUrl, addPETransaction,username, TermGridConstants.password).getBody().as(AddPETransactionResponse.class);
    }
    public static Response post(String token, AddPETransactionRequest addPETransaction){
        return RestResource.post(token,addPETransactionUrl, addPETransaction);
    }


}
