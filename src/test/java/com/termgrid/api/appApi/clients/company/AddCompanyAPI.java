package com.termgrid.api.appApi.clients.company;

import com.termgrid.api.appApi.pojo.company.AddCompanyRequest;
import com.termgrid.api.appApi.pojo.element.AddPETransactionRequest;
import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.addCompanyUrl;

public class AddCompanyAPI {

    @Step
    public static Response post(AddCompanyRequest addCompanyRequest, String username, String password){
        return RestResource.post(addCompanyUrl, addCompanyRequest,username,password);
    }


}
