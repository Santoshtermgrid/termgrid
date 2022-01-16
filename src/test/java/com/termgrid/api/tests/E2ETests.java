package com.termgrid.api.tests;

import com.termgrid.api.appApi.pojos.users.CustomerLoginResponse;
import com.termgrid.api.appApi.pojos.users.RegisterResponse;
import com.termgrid.api.services.UserFlowContext;
import com.termgrid.api.services.UserFlowService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.termgrid.api.Validations.UserFlowValidations.Validator_after_admin_verification_login_to_user_account;
import static com.termgrid.api.Validations.UserFlowValidations.Validator_newUserRegistration;

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
        Validator_newUserRegistration(userFlowContext, registerResponse);
    }



    @Test(dependsOnMethods = {"verify_newUserRegistration"})
    public void verify_admin_approval_registration_flow(){
        RegisterResponse registerResponse = RegisterResponse.builder().email(userFlowContext.getAdminEmail()).build();
        String verificationForNewUser = userFlowService.adminVerificationForNewUser(registerResponse);
        Assert.assertEquals(verificationForNewUser, "Email Verified successfully");
    }

    @Test(dependsOnMethods = {"verify_admin_approval_registration_flow","verify_newUserRegistration"})
    public void after_admin_verification_login_to_user_account(){
        RegisterResponse registerResponse = RegisterResponse.builder().email(userFlowContext.getAdminEmail()).build();
        CustomerLoginResponse customerLoginResponse = userFlowService.newUserLoginPostVerification(registerResponse);
        Validator_after_admin_verification_login_to_user_account(customerLoginResponse);

    }
    @Test
    public void check_after_userlogin_create_company(){

    }
    @Test
    public void user_invites_team_members_to_company(){

    }

    @Test
    public void check_team_members_status_is_pending(){

    }
    @Test
    public void verify_admin_approval_flow_for_team_members(){

    }

    @Test
    public void check_team_members_status_is_accepted_after_admin_approval_flow(){

    }

    @Test
    public void check_userbydefault_is_disabled_creating_new_transaction(){

    }

    @Test
    public void verify_post_admin_approval_create_transaction_is_enabled_for_user(){

    }

    @Test
    public void verify_team_member_is_allowed_to_login_after_approval(){

    }

    @Test
    public void verify_team_member_is_allowed_to_create_transaction(){

    }


}
