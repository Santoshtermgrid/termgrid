package com.termgrid.api.appApi.clients.users;

import com.termgrid.api.appApi.pojo.users.CustomerLoginRequest;
import com.termgrid.api.commons.RestResource;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.customerLoginUrl;

public class CustomerLoginAPI {

    @Step
    public static Response post(CustomerLoginRequest customerLogin){
        return RestResource.post(customerLoginUrl, customerLogin);
    }

}
