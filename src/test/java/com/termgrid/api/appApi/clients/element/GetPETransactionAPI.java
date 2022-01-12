package com.termgrid.api.appApi.clients.element;

import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.getPETransactionUrl;

public class GetPETransactionAPI {


    @Step
    public static Response get(HashMap<String, String> getPETransactionPathParam){
        return RestResource.getbyPathParams(getPETransactionUrl, getPETransactionPathParam);
    }

}
