package com.termgrid.api.appApi.clients.admin;

import com.termgrid.api.appApi.clients.commons.restassured.RestResource;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.util.List;
import java.util.Map;

import static com.termgrid.api.appApi.endpoints.ApiEndPoints.createJoinLinkUrl;

public class CreateJoinLinkAPI {


    @Step
    public static List<Map<String, Object>>  getInvite(String username, String password){
        return RestResource.get(createJoinLinkUrl,username,password).as(new TypeRef<List<Map<String,Object>>>() {});
    }
}
