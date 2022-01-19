package com.termgrid.api.appApi.clients.assignlendertotransaction;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import com.termgrid.api.appApi.pojos.assignlendertotransaction.AssignLenderToTransPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.assignLenderToTransactionUrl;

public class AssignLenderToTransactionAPI {
    @Step
    public static Response post(AssignLenderToTransPojo assignLenderToTrans){
        return RestResource.post(assignLenderToTransactionUrl, assignLenderToTrans);
    }

    @Step
    public static Response post(AssignLenderToTransPojo assignLenderToTrans, String username, String password){
        return RestResource.post(assignLenderToTransactionUrl, assignLenderToTrans,username,password);
    }
}
