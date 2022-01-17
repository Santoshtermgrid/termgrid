package com.termgrid.api.tests;

import com.termgrid.api.appApi.clients.commons.restassured.StatusCode;
import com.termgrid.api.appApi.clients.commons.utils.DataLoader;
import com.termgrid.api.appApi.clients.element.AddPETransactionAPI;
import com.termgrid.api.appApi.pojos.company.AddCompanyResponse;
import com.termgrid.api.appApi.pojos.element.AddPETransactionRequest;
import com.termgrid.api.appApi.pojos.element.AddPETransactionResponse;
import com.termgrid.api.appApi.pojos.element.IndustryDTOSet;
import com.termgrid.api.appApi.pojos.users.CustomerLoginResponse;
import com.termgrid.api.appApi.pojos.users.GetUserDetailsResponse;
import com.termgrid.api.appApi.pojos.users.RegisterResponse;
import com.termgrid.api.services.UserFlowContext;
import com.termgrid.api.services.UserFlowService;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.termgrid.api.Validations.UserFlowValidations.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class E2ETests extends BaseTest{

    UserFlowService userFlowService = new UserFlowService();
    @Test
    public void testCreateCompanyFlow(){
        UserFlowService userFlowService = new UserFlowService();
        userFlowService.createCompany();
    }

   /* Verify Company name should be always unique
    Utility to Verify User belonging to one company cannot be part of another company
    Utility to Verify Update Member Transaction Rights by default user is disabled for creating transaction
    Utility to create  a new user & verify the approval flow & login into account
    Utility Create a  new company  for the newly created user
    Utility Add a team mate to com.github.javafaker.Company
    Utility to Verfiy on approval of inviation of Team mate moves from pending to accepted
    Utility Verify Update Member Transaction Rights enables user to create transaction
    Utility to Create a new transanction
    Utility Delete a new transaction*/

    UserFlowContext userFlowContext= new UserFlowContext();

    @Test
    public void verify_newUserRegistration(){
        RegisterResponse registerResponse = userFlowService.newUserRegistration();
        userFlowContext.setUser1Emailid(registerResponse.email);
        userFlowContext.setUser1UserId(registerResponse.userId);
        Validator_newUserRegistration( registerResponse);
    }



    @Test(dependsOnMethods = {"verify_newUserRegistration"})
    public void verify_admin_approval_registration_flow(){
        RegisterResponse registerResponse = RegisterResponse.builder().email(userFlowContext.getUser1Emailid()).build();
        String verificationForNewUser = userFlowService.adminVerificationForNewUser(registerResponse);
        Assert.assertEquals(verificationForNewUser, "Email Verified successfully");
    }

    @Test(dependsOnMethods = {"verify_admin_approval_registration_flow","verify_newUserRegistration"})
    public void after_admin_verification_login_to_user_account(){
        RegisterResponse registerResponse = RegisterResponse.builder().email(userFlowContext.getUser1Emailid()).build();
        CustomerLoginResponse customerLoginResponse = userFlowService.newUserLoginPostVerification(registerResponse);
        Validator_after_admin_verification_login_to_user_account(customerLoginResponse);

    }
    @Test(dependsOnMethods = {"verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account"})
    public void check_after_userlogin_create_company(){
        AddCompanyResponse newCompany = userFlowService.createNewCompany(CustomerLoginResponse.builder().email(userFlowContext.getUser1Emailid()).build());
        userFlowContext.setCompanyId(newCompany.id);
        userFlowContext.setIndustriesId(newCompany.industries.get(0).id);
        userFlowContext.setIndustryName(newCompany.industries.get(0).name);
    }
    @Test(dependsOnMethods = {"verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company"})
    public void user_invites_team_members_to_company(){
        RegisterResponse registerResponse = RegisterResponse.builder().email(userFlowContext.getUser1Emailid()).build();
        AddCompanyResponse addCompanyResponse= AddCompanyResponse.builder().id(userFlowContext.getCompanyId()).build();
        List<Map<String, Object>> registerNewDealTeamMemberResponse = userFlowService.registerNewDealTeamMemberResponse(registerResponse, addCompanyResponse);
        userFlowContext.setTeamMember1EmailId(registerNewDealTeamMemberResponse.get(0).get("email").toString());
        userFlowContext.setTeamMember1UserId(registerNewDealTeamMemberResponse.get(0).get("userId").toString());
        userFlowContext.setTeamMember1invitationStatus(registerNewDealTeamMemberResponse.get(0).get("invitationStatus").toString());
        Assert.assertNotNull(userFlowContext.getUser1Emailid());
        Assert.assertNotNull(userFlowContext.getUser1UserId());
    }

    @Test(dependsOnMethods = {"verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company"})
    public void check_team_members_status_is_pending(){
        Assert.assertEquals(userFlowContext.getTeamMember1invitationStatus(),"PENDING");
    }
    @Test(dependsOnMethods = {"verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company","check_team_members_status_is_pending"})
    public void verify_admin_approval_flow_for_team_members(){
        String adminJoinLinkUrl = userFlowService.adminJoinLinkUrl(userFlowContext.getTeamMember1EmailId());
        userFlowContext.setAdminJoinLinkUrl(adminJoinLinkUrl);
        Assert.assertNotNull(adminJoinLinkUrl);
    }

    @Test(dependsOnMethods = {"verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company","check_team_members_status_is_pending","verify_admin_approval_flow_for_team_members"})
    public void check_team_members_status_is_accepted_after_admin_approval_flow(){
        userFlowService.newAdminTeamUserPasswordSet(userFlowContext.getAdminJoinLinkUrl());
        GetUserDetailsResponse userDetails = userFlowService.getUserDetails(userFlowContext.getTeamMember1EmailId());
        userFlowContext.setTeamMember1createTransactionStatus(userDetails.createTransaction);
        Assert.assertTrue(userDetails.emailVerified);
        Assert.assertTrue(userDetails.isAdmin);
    }

    @Test(dependsOnMethods = {"check_team_members_status_is_accepted_after_admin_approval_flow","verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company","check_team_members_status_is_pending","verify_admin_approval_flow_for_team_members"})
    public void check_userbydefault_is_disabled_creating_new_transaction(){
        Assert.assertFalse(userFlowContext.getTeamMember1createTransactionStatus());
    }

    @Test(dependsOnMethods = {"check_userbydefault_is_disabled_creating_new_transaction","check_team_members_status_is_accepted_after_admin_approval_flow","verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company","check_team_members_status_is_pending","verify_admin_approval_flow_for_team_members"})
    public void verify_post_admin_approval_create_transaction_is_enabled_for_user(){
        userFlowService.UpdateTransactionRights(RegisterResponse.builder().email(userFlowContext.getUser1Emailid()).build());
        GetUserDetailsResponse userDetails = userFlowService.getUserDetails(userFlowContext.getUser1Emailid());
        Assert.assertTrue(userDetails.createTransaction,"Verification for User 1"+userFlowContext.getUser1Emailid());


        userFlowService.UpdateTransactionRights(RegisterResponse.builder().email(userFlowContext.getTeamMember1EmailId()).build());
        userDetails = userFlowService.getUserDetails(userFlowContext.getUser1Emailid());
        Assert.assertTrue(userDetails.createTransaction,"Verification for Team Member 1"+userFlowContext.getTeamMember1EmailId());

    }

    @Test(dependsOnMethods = {"verify_post_admin_approval_create_transaction_is_enabled_for_user","check_userbydefault_is_disabled_creating_new_transaction","check_team_members_status_is_accepted_after_admin_approval_flow","verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company","check_team_members_status_is_pending","verify_admin_approval_flow_for_team_members"})
    public void verify_team_member_is_allowed_to_login_after_approval(){
        CustomerLoginResponse customerLoginResponse = userFlowService.newUserLoginPostVerification(RegisterResponse.builder().email(userFlowContext.getTeamMember1EmailId()).build());
        Validator_login_checks(customerLoginResponse);

    }
    @Test(dependsOnMethods = {"verify_post_admin_approval_create_transaction_is_enabled_for_user","check_userbydefault_is_disabled_creating_new_transaction","check_team_members_status_is_accepted_after_admin_approval_flow","verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company","check_team_members_status_is_pending","verify_admin_approval_flow_for_team_members"})
    public void verify_user1_is_allowed_to_login_after_approval(){
        CustomerLoginResponse customerLoginResponse = userFlowService.newUserLoginPostVerification(RegisterResponse.builder().email(userFlowContext.getUser1Emailid()).build());
        Validator_login_checks(customerLoginResponse);

    }



    @Test(dependsOnMethods = {"verify_user1_is_allowed_to_login_after_approval","verify_post_admin_approval_create_transaction_is_enabled_for_user","check_userbydefault_is_disabled_creating_new_transaction","check_team_members_status_is_accepted_after_admin_approval_flow","verify_admin_approval_registration_flow","verify_newUserRegistration","after_admin_verification_login_to_user_account","check_after_userlogin_create_company","user_invites_team_members_to_company","check_team_members_status_is_pending","verify_admin_approval_flow_for_team_members"})
    public void verify_team_member_is_allowed_to_create_transaction() {
        AddPETransactionRequest payload = addPETransaactionPayload();

        AddPETransactionResponse response = AddPETransactionAPI.post(payload, userFlowContext.getTeamMember1EmailId());

        userFlowContext.setPeTransactionId(response.peTransactionId);
        Assert.assertFalse(response.deleted);
        Assert.assertEquals(response.companyId,userFlowContext.getCompanyId());
    }

    private AddPETransactionRequest addPETransaactionPayload() {
        return AddPETransactionRequest.builder().
                companyId(userFlowContext.getCompanyId())
                .companyToBuy("Test Company")
                .peTransactionTitle("Automatin1")
                .targetDate(1640889000000l)
                .industryDTOSet(Arrays.asList(IndustryDTOSet.builder().active(true)
                        .id(userFlowContext.getIndustriesId())
                        .approved(true).name(userFlowContext.getIndustryName()).build())).build();
    }
}
