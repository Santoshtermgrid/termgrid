package com.termgrid.api.appApi.element;

import com.termgrid.api.RestResource;
import com.termgrid.api.appApi.pojo.element.AddPETransactionPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.termgrid.api.ApiEndPoints.addPETransactionUrl;
import static com.termgrid.api.ApiEndPoints.getPETransactionUrl;

public class GetPETransactionAPI {


    @Step
    public static Response get(HashMap<String, String> getPETransactionPathParam){
        return RestResource.getbyPathParams(getPETransactionUrl, getPETransactionPathParam);
    }

}
