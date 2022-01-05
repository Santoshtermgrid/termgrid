package com.termgrid.api.appApi.element;

import com.termgrid.api.RestResource;
import com.termgrid.api.appApi.pojo.element.AddPETransactionPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.ApiEndPoints.*;

public class AddPETransactionAPI {

    @Step
    public static Response post(AddPETransactionPojo addPETransaction){
        return RestResource.post(addPETransactionUrl, addPETransaction);
    }
    public static Response post(String token, AddPETransactionPojo addPETransaction){
        return RestResource.post(token,addPETransactionUrl, addPETransaction);
    }


}
