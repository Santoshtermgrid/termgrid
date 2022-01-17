package com.termgrid.api.appApi.clients.assignlender;

import com.termgrid.api.appApi.pojo.assignlendertotrans.AssignLenderToTransPojo;
import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static com.termgrid.api.appApi.endpoints.ApiEndPoints.assignLenderToTransactionUrl;

public class AssignLenderToTransactionAPI {
    @Step
    public static Response post(AssignLenderToTransPojo assignLenderToTrans){
        return RestResource.post(assignLenderToTransactionUrl, assignLenderToTrans);
    }

    public static Response post(AssignLenderToTransPojo assignLenderToTrans, String username, String password){
        return RestResource.post(assignLenderToTransactionUrl, assignLenderToTrans,username,password);
    }
}
