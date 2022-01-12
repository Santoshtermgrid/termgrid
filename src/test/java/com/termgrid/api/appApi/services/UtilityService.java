package com.termgrid.api.appApi.services;

import com.termgrid.api.appApi.clients.admin.CreateJoinLinkAPI;
import com.termgrid.api.appApi.clients.admin.JoinTeamLoginAPI;
import com.termgrid.api.appApi.clients.company.AddCompanyAPI;
import com.termgrid.api.appApi.clients.invite.InviteAPI;
import com.termgrid.api.appApi.clients.users.CustomerLoginAPI;
import com.termgrid.api.appApi.clients.users.RegisterLoginAPI;
import com.termgrid.api.appApi.clients.users.VerifyEmailAPI;
import com.termgrid.api.appApi.pojo.admin.JoinTeamLogin;
import com.termgrid.api.appApi.pojo.company.AddCompanyRequest;
import com.termgrid.api.appApi.pojo.company.AddCompanyResponse;
import com.termgrid.api.appApi.pojo.company.Geography;
import com.termgrid.api.appApi.pojo.company.Industry;
import com.termgrid.api.appApi.pojo.invite.InviteRequest;
import com.termgrid.api.appApi.pojo.users.CustomerLoginRequest;
import com.termgrid.api.appApi.pojo.users.CustomerLoginResponse;
import com.termgrid.api.appApi.pojo.users.RegisterRequest;
import com.termgrid.api.appApi.pojo.users.RegisterResponse;
import com.termgrid.api.commons.RestResource;
import com.termgrid.api.commons.utils.DataLoader;
import com.termgrid.api.commons.utils.FakerUtils;
import com.termgrid.api.commons.utils.TermGridConstants;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityService {
    @Step
    public void createCompany() {
        RegisterResponse registerResponse = RegisterLoginAPI.post(getLoginInfo());
        HashMap<String, String> email = new HashMap<>();
        email.put("emailId", registerResponse.email);
        String message = VerifyEmailAPI.get(email).getBody().jsonPath().getString("message");
        CustomerLoginResponse customerLoginResponse = CustomerLoginAPI.post(CustomerLoginRequest.builder().email(registerResponse.email).password(TermGridConstants.password).build()).getBody().as(CustomerLoginResponse.class);
        AddCompanyResponse addCompanyResponse = AddCompanyAPI.post(addCompanyRequest(), customerLoginResponse.email, TermGridConstants.password).getBody().as(AddCompanyResponse.class);
        List<Map<String, Object>> responseBody = InviteAPI.post(teamMember(addCompanyResponse), registerResponse.email, TermGridConstants.password).getBody().as(new TypeRef<List<Map<String, Object>>>() {
        });
        String teamMemberEmail = responseBody.get(0).get("email").toString();
        List<Map<String, Object>> invite = CreateJoinLinkAPI.getInvite(TermGridConstants.admin, TermGridConstants.adminpassword);
        String joinLink = null;
        for (Map<String, Object> map : invite) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.equals("email") && value.equals(teamMemberEmail)) {
                    joinLink = map.get("inviteLink").toString();
                    break;
                }
                if (joinLink != null) {
                    break;
                }
            }
        }
        joinLink = joinLink.split("join-team")[1];
        joinLink = DataLoader.getInstance().getbaseurl() + "join-team" + joinLink;
        ResponseBody body = RestResource.get(joinLink, TermGridConstants.admin, TermGridConstants.adminpassword).getBody();
        Response response = JoinTeamLoginAPI.post(joinLink, getJoinTeamLoginBuilder(), TermGridConstants.admin, TermGridConstants.adminpassword);
        System.out.println(response);

    }

    private JoinTeamLogin getJoinTeamLoginBuilder() {
        return JoinTeamLogin.builder().newPassword(TermGridConstants.password).confirmPassword(TermGridConstants.password).build();
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
