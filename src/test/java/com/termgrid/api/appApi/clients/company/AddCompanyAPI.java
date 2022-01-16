package com.termgrid.api.appApi.clients.company;

import com.termgrid.api.appApi.pojos.company.AddCompanyRequest;
import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.addCompanyUrl;

public class AddCompanyAPI {

    @Step
    public static Response post(AddCompanyRequest addCompanyRequest, String username, String password){
        return RestResource.post(addCompanyUrl, addCompanyRequest,username,password);
    }


}
