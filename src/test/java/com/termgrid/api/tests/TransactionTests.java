package com.termgrid.api.tests;
/*
import com.termgrid.api.appApi.clients.commons.restassured.StatusCode;
import com.termgrid.api.appApi.clients.element.AddPETransactionAPI;
import com.termgrid.api.appApi.clients.element.DeletePETransactionApi;
import com.termgrid.api.appApi.clients.element.GetPETransactionAPI;
import com.termgrid.api.appApi.pojos.element.AddPETransactionRequest;
import com.termgrid.api.appApi.pojos.element.GetPETransactionResponse;
import com.termgrid.api.appApi.pojos.element.IndustryDTOSet;
import com.termgrid.api.appApi.clients.commons.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("TermGrid API Flows")
@Feature("TermGrid AddTransaction API")
public class TransactionTests extends BaseTest {
    String transactionId=null;
    @Story("TermGrid AddTransaction API")
    @Description("should be able to create a peTransaction")
    @Test(description = "should be able to create a peTransaction")
    public void ShouldBeAbleToCreateTransaction() {
        AddPETransactionRequest payload = AddPETransactionRequest.builder().
                companyId("2")
                .companyToBuy("Test Company")
                .peTransactionTitle("Automatin1")
                .targetDate(1640889000000l)
                .industryDTOSet(Arrays.asList(IndustryDTOSet.builder().active(true)
                        .id("10966")
                        .approved(true).name("Chemicals").build())).build();

        Response response = AddPETransactionAPI.post(payload, DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword());

        transactionId=response.jsonPath().getString("peTransactionId");

        System.out.println("TransactionId==="+response.jsonPath().getString("peTransactionId"));

        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Story("TermGrid DeleteTransaction API")
    @Description("should be able delete  peTransaction")
    @Test(description = "ShouldbeAbletoDeleteTransaction",dependsOnMethods = { "CheckTransactionStatusBeforeDelete" })
    public void ShouldbeAbletoDeleteTransaction() {
        HashMap<String ,String> pathVariable=new HashMap<>();
        pathVariable.put("transactionid",transactionId);

        Response response = DeletePETransactionApi.deletebyPathParam(pathVariable);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }


    @Story("TermGrid AddTransaction API")
    @Description("With Wrong AUth, user should not able to delete transaction")
    @Test(description = "With Wrong AUth, user should not able to delete transaction")
    public void ShouldNotbeAbleCreateTransactionWithWrongAuth() {
        AddPETransactionRequest payload = AddPETransactionRequest.builder().
                companyId("2")
                .companyToBuy("Test Company")
                .peTransactionTitle("Automatin1")
                .targetDate(1640889000000l)
                .industryDTOSet(Arrays.asList(IndustryDTOSet.builder().active(true)
                        .id("10966")
                        .approved(true).name("Chemicals").build())).build();
        Response response = AddPETransactionAPI.post("Bc9775765",payload);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Story("TermGrid GetTransaction API")
    @Description("Check Transaction status it should be deleted=true")
    @Test(description = "Check Transaction status it should be deleted=true",dependsOnMethods = { "ShouldbeAbletoDeleteTransaction" })
    public void CheckTransactionStatusAfterDelete() {
       HashMap<String, String> pathParams= new HashMap<>();
       pathParams.put("transactionid",transactionId);
        Response response = GetPETransactionAPI.get(pathParams);
        GetPETransactionResponse getPETransactionResponse = GetPETransactionAPI.get(pathParams).getBody().as(GetPETransactionResponse.class);
        getPETransactionResponse.getDeleted();

        String deleted = response.getBody().jsonPath().getString("deleted");
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        assertThat(deleted, equalTo("true"));
    }

    @Story("TermGrid GetTransaction API")
    @Description("CheckTransactionStatusBeforeDelete")
    @Test(description = "Check Transaction status it should be deleted=false",dependsOnMethods = { "ShouldBeAbleToCreateTransaction" })
    public void CheckTransactionStatusBeforeDelete() throws InterruptedException {
        HashMap<String, String> pathParams= new HashMap<>();
        pathParams.put("transactionid",transactionId);
        Response response = GetPETransactionAPI.get(pathParams);
        String deleted = response.getBody().jsonPath().getString("deleted");
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        assertThat(deleted, equalTo("false"));
    }

}*/
