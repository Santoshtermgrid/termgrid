package com.termgrid.api.services;

import com.termgrid.api.appApi.clients.admin.CreateJoinLinkAPI;
import com.termgrid.api.appApi.clients.admin.JoinTeamLoginAPI;
import com.termgrid.api.appApi.clients.admin.UpdateTransactionRightsAPI;
import com.termgrid.api.appApi.clients.company.AddCompanyAPI;
import com.termgrid.api.appApi.clients.invite.InviteAPI;
import com.termgrid.api.appApi.clients.users.CustomerLoginAPI;
import com.termgrid.api.appApi.clients.users.GetUserDetails;
import com.termgrid.api.appApi.clients.users.RegisterLoginAPI;
import com.termgrid.api.appApi.clients.users.VerifyEmailAPI;
import com.termgrid.api.appApi.pojos.admin.JoinTeamLoginRequest;
import com.termgrid.api.appApi.pojos.admin.UpdateTransactionRequest;
import com.termgrid.api.appApi.pojos.company.AddCompanyRequest;
import com.termgrid.api.appApi.pojos.company.AddCompanyResponse;
import com.termgrid.api.appApi.pojos.company.Geography;
import com.termgrid.api.appApi.pojos.company.Industry;
import com.termgrid.api.appApi.pojos.invite.InviteRequest;
import com.termgrid.api.appApi.pojos.users.*;
import com.termgrid.api.appApi.clients.commons.utils.DataLoader;
import com.termgrid.api.appApi.clients.commons.utils.FakerUtils;
import com.termgrid.api.appApi.clients.commons.utils.TermGridConstants;
import io.qameta.allure.Step;
import io.restassured.common.mapper.TypeRef;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFlowService {


    @Step
    public void createCompany() {
        RegisterResponse registerResponse = newUserRegistration();
        String message =adminVerificationForNewUser(registerResponse);
        CustomerLoginResponse customerLoginResponse = newUserLoginPostVerification(registerResponse);
        AddCompanyResponse addCompanyResponse = createNewCompany(customerLoginResponse);
        String registerNewDealTeamMemberEmailId = registerNewDealTeamMember(registerResponse, addCompanyResponse);
        String joinLink = adminJoinLinkUrl(registerNewDealTeamMemberEmailId);
        String response = newAdminTeamUserPasswordSet(joinLink);
        UpdateTransactionRights(registerResponse);
    }

    @Step
    public void UpdateTransactionRights(RegisterResponse registerResponse ) {
        UpdateTransactionRequest updateTransactionRequest = updateTransactionRequest(registerResponse);
        UpdateTransactionRightsAPI.post(updateTransactionRequest,TermGridConstants.admin,TermGridConstants.adminpassword);
    }
    @Step
    public UpdateTransactionRequest updateTransactionRequest(RegisterResponse registerResponse) {
        return UpdateTransactionRequest.builder().email(registerResponse.getEmail()).status(true).build();
    }

    @Step
    public GetUserDetailsResponse getUserDetails(String username) {
        return GetUserDetails.get(username);
    }
    @Step
    public String newAdminTeamUserPasswordSet(String joinLink) {
        return JoinTeamLoginAPI.post(joinLink, getJoinTeamLoginBuilder(), TermGridConstants.admin, TermGridConstants.adminpassword);
    }
    @Step
    public String fetchJoinLinkUrl(String teamMemberEmail, List<Map<String, Object>> invite) {
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
        return joinLink;
    }

    @Step
    public String adminJoinLinkUrl(String teamMemberEmail) {
        List<Map<String, Object>> invite = CreateJoinLinkAPI.getInvite(TermGridConstants.admin, TermGridConstants.adminpassword);
        String joinLinkUrl = fetchJoinLinkUrl(teamMemberEmail, invite);
        joinLinkUrl = joinLinkUrl.split("join-team")[1];
        return  DataLoader.getInstance().getbaseurl() + "api/rest/users/join-team" + joinLinkUrl;
    }
    @Step
    public String  registerNewDealTeamMember(RegisterResponse registerResponse, AddCompanyResponse addCompanyResponse) {
        List<Map<String, Object>> responseBody = InviteAPI.post(teamMember(addCompanyResponse), registerResponse.email, TermGridConstants.password).getBody().as(new TypeRef<List<Map<String, Object>>>() {
        });
        return responseBody.get(0).get("email").toString();
    }
    @Step
    public List<Map<String, Object>>   registerNewDealTeamMemberResponse(RegisterResponse registerResponse, AddCompanyResponse addCompanyResponse) {
       return InviteAPI.post(teamMember(addCompanyResponse), registerResponse.email, TermGridConstants.password).getBody().as(new TypeRef<List<Map<String, Object>>>() {
        });
    }
    @Step
    public AddCompanyResponse createNewCompany(CustomerLoginResponse customerLoginResponse) {
        return AddCompanyAPI.post(addCompanyRequest(), customerLoginResponse.email, TermGridConstants.password).getBody().as(AddCompanyResponse.class);
    }
    @Step
    public CustomerLoginResponse newUserLoginPostVerification(RegisterResponse registerResponse) {
        return CustomerLoginAPI.post(CustomerLoginRequest.builder().email(registerResponse.email).password(TermGridConstants.password).build()).getBody().as(CustomerLoginResponse.class);
    }
    @Step
    public String adminVerificationForNewUser(RegisterResponse registerResponse) {
        HashMap<String, String> email = new HashMap<>();
        email.put("emailId", registerResponse.email);
        return VerifyEmailAPI.get(email).getBody().jsonPath().getString("message");
    }
    @Step
    public RegisterResponse newUserRegistration() {
        return RegisterLoginAPI.post(getLoginInfo());
    }
    @Step
    public JoinTeamLoginRequest getJoinTeamLoginBuilder() {
        return JoinTeamLoginRequest.builder().newPassword(TermGridConstants.password).confirmPassword(TermGridConstants.password).build();
    }
    @Step
    public InviteRequest teamMember(AddCompanyResponse addCompanyResponse) {
        return InviteRequest.builder().firstName(FakerUtils.Faker().name().firstName()).lastName(FakerUtils.Faker().name().lastName())
                .email(FakerUtils.generateEmail()).elementType("LENDER").companyId(addCompanyResponse.getId()).admin(true).build();
    }
    @Step
    public AddCompanyRequest addCompanyRequest() {
        return AddCompanyRequest.builder().name(FakerUtils.Faker().superhero().name() + "" + FakerUtils.generateUUID()).
                industries(Arrays.asList(Industry.builder().build())).geographies(Arrays.asList(Geography.builder().build())).build();
    }
    @Step
    public RegisterRequest getLoginInfo() {
        return RegisterRequest.builder().firstName(FakerUtils.generateName())
                .lastName(FakerUtils.generateLastName()).phoneNumber(FakerUtils.generatePhoneNumber())
                .password(TermGridConstants.password)
                .email("santosh+" + FakerUtils.generateUUID() + "@termgrid.com").build();
    }
}
