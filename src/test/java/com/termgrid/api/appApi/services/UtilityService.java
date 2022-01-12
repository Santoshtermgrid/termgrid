package com.termgrid.api.appApi.services;

import com.termgrid.api.appApi.clients.admin.CreateJoinLink;
import com.termgrid.api.appApi.clients.company.AddCompanyAPI;
import com.termgrid.api.appApi.clients.invite.InviteAPI;
import com.termgrid.api.appApi.clients.users.CustomerLoginAPI;
import com.termgrid.api.appApi.clients.users.RegisterLoginAPI;
import com.termgrid.api.appApi.clients.users.VerifyEmailAPI;
import com.termgrid.api.appApi.pojo.company.AddCompanyRequest;
import com.termgrid.api.appApi.pojo.company.AddCompanyResponse;
import com.termgrid.api.appApi.pojo.company.Geography;
import com.termgrid.api.appApi.pojo.company.Industry;
import com.termgrid.api.appApi.pojo.invite.InviteRequest;
import com.termgrid.api.appApi.pojo.invite.InviteResponse;
import com.termgrid.api.appApi.pojo.users.CustomerLoginRequest;
import com.termgrid.api.appApi.pojo.users.CustomerLoginResponse;
import com.termgrid.api.appApi.pojo.users.RegisterRequest;
import com.termgrid.api.appApi.pojo.users.RegisterResponse;
import com.termgrid.api.commons.RestResource;
import com.termgrid.api.commons.utils.TermGridConstants;
import com.termgrid.api.commons.utils.FakerUtils;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

public class UtilityService {


    @Step
    public void createCompany(){
        //register
        RegisterResponse registerResponse = RegisterLoginAPI.post(getLoginInfo());
        HashMap<String ,String> email = new HashMap<>();
        email.put("emailId", registerResponse.email);
        String message = VerifyEmailAPI.get(email).getBody().jsonPath().getString("message");
        CustomerLoginResponse customerLoginResponse = CustomerLoginAPI.post(CustomerLoginRequest.builder().email(registerResponse.email).password(TermGridConstants.password).build()).getBody().as(CustomerLoginResponse.class);
        System.out.println(customerLoginResponse.authToken);
        System.out.println(customerLoginResponse.userId);
        System.out.println(customerLoginResponse.emailVerified);
        AddCompanyResponse addCompanyResponse = AddCompanyAPI.post(addCompanyRequest(), customerLoginResponse.email, TermGridConstants.password).getBody().as(AddCompanyResponse.class);
        //System.out.println(responseBody);
        List<Map<String,Object>> responseBody = null;
        responseBody = InviteAPI.post(teamMember(addCompanyResponse), registerResponse.email, TermGridConstants.password).getBody().as(new TypeRef<List<Map<String,Object>>>() {});
        System.out.println(responseBody.get(0).get("userId"));
        System.out.println(responseBody.get(0).get("email"));
        System.out.println(responseBody.get(0).get("companyId"));
        String teamMemberEmail  = responseBody.get(0).get("email").toString();
        List<Map<String, Object>> invite = CreateJoinLink.getInvite(TermGridConstants.admin, TermGridConstants.adminpassword);
String joinLink=null;
        for (Map<String, Object> map : invite) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
System.out.println(key);
                Object value = entry.getValue();
if(key.equals("email") && value.equals(teamMemberEmail)){
  System.out.println(map.get("inviteLink"));
    joinLink=map.get("inviteLink").toString();
  break;
}

            }
        }
        ResponseBody body = RestResource.get(joinLink, TermGridConstants.admin, TermGridConstants.adminpassword).getBody();
        /*Optional<Map<String, Object>> email1 = invite.stream()
                .peek(System.out::println)
                .filter(hashmap -> ((hashmap("email")) == "aasim+nov27_1santanders@inncretech.com"))

                .findFirst();*/
        System.out.println(body);
/*
        {"firstName":"Santosh","lastName":"baby","email":"santosh
            "elementType":"LENDER","companyId":"453005","admin":true}*/
        //CreateCompany

    }

    private InviteRequest teamMember(AddCompanyResponse addCompanyResponse) {
        return InviteRequest.builder().firstName(FakerUtils.Faker().name().firstName()).lastName(FakerUtils.Faker().name().lastName())
                .email(FakerUtils.generateEmail()).elementType("LENDER").companyId(addCompanyResponse.getId()).admin(true).build();
    }

    private AddCompanyRequest addCompanyRequest() {
        return AddCompanyRequest.builder().name(FakerUtils.Faker().superhero().name() + "" + FakerUtils.generateUUID()).
                industries(Arrays.asList(Industry.builder().build())).geographies(Arrays.asList(Geography.builder().build())).build();
    }

    private RegisterRequest getLoginInfo() {
        return RegisterRequest.builder().firstName(FakerUtils.generateName())
                .lastName(FakerUtils.generateLastName()).phoneNumber(FakerUtils.generatePhoneNumber())
                .password(TermGridConstants.password)
                .email("santosh+" + FakerUtils.generateUUID() + "@termgrid.com").build();
    }
}
