package com.termgrid.api.appApi.element;

import com.termgrid.api.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.termgrid.api.ApiEndPoints.*;

public class DeletePETransactionApi {

    @Step
    public static Response deletebyPathParam(HashMap<String, String> pathparams ){
        return RestResource.deletebyPathParam(deleteTransactionUrl,"",pathparams);
    }




}
