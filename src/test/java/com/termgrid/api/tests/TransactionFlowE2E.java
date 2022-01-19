package com.termgrid.api.tests;

import com.termgrid.api.appApi.clients.commons.restassured.StatusCode;
import com.termgrid.api.services.TransactionFlowService;
import com.termgrid.api.services.UserFlowContext;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransactionFlowE2E {
    TransactionFlowService transactionFlowService = new TransactionFlowService();
    Response response = null;
    UserFlowContext userFlowContext = new UserFlowContext();
    boolean result;

    @Test
    @Description("This test will validate the new transaction's creation with the status code")
    public void create_transaction(){
        response = transactionFlowService.CreateTransaction();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        result = response.jsonPath().getString("peTransactionId")!=null;
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        userFlowContext.setPeTransactionId(response.jsonPath().getString("peTransactionId"));
    }

    @Test(dependsOnMethods = {"create_transaction"})
    @Description("This test will validate the new termsheet's creation under transaction with the status code")
    public void create_term_sheet(){
        response = transactionFlowService.createTermSheet();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Test(dependsOnMethods = {"create_term_sheet"})
    @Description("This test will validate the new dataroom's creation under transaction with the status code")
    public void create_DataRoom() {
        response = transactionFlowService.createDataRoom();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    /*@Test(dependsOnMethods = {"create_DataRoom"})
    public void add_the_documents_into_dataRoom() {
        response = transactionFlowService.addTheDocumentsIntoDataRoom();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }*/

    @Test(dependsOnMethods = {"create_DataRoom"})
    @Description("This test will validate the new lender's creation under transaction with the status code")
    public void create_new_lender() {
        response = transactionFlowService.createNewLenderForCompany();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Test(dependsOnMethods = {"create_new_lender"})
    @Description("This test will validate the created lender is being assign to transaction along with the status code")
    public void assign_newly_created_lender_to_transaction() {
        response = transactionFlowService.assignNewlyCreatedLenderToTransaction();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }
}