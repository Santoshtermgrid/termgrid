package com.termgrid.api.Validations;

import com.termgrid.api.appApi.pojos.users.CustomerLoginResponse;
import com.termgrid.api.appApi.pojos.users.RegisterResponse;
import com.termgrid.api.services.UserFlowContext;
import org.testng.Assert;

public class UserFlowValidations {

    public static void Validator_newUserRegistration(RegisterResponse registerResponse) {
        Assert.assertFalse(registerResponse.emailVerified);
        Assert.assertTrue(registerResponse.isActive);

    }

    public static void Validator_after_admin_verification_login_to_user_account(CustomerLoginResponse customerLoginResponse){
        Assert.assertFalse(customerLoginResponse.createTransaction);
        Assert.assertTrue(customerLoginResponse.emailVerified);
        Assert.assertTrue(customerLoginResponse.isActive);
    }

    public static  void Validator_login_checks(CustomerLoginResponse customerLoginResponse)
    {
        Assert.assertTrue(customerLoginResponse.isActive);
        Assert.assertTrue(customerLoginResponse.createTransaction);
        Assert.assertTrue(customerLoginResponse.emailVerified);
    }
}
