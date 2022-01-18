package com.termgrid.api.tests;

import com.termgrid.api.appApi.clients.commons.restassured.StatusCode;
import com.termgrid.api.services.TransactionFlowService;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransactionFlowE2E {
    TransactionFlowService transactionFlowService = new TransactionFlowService();
    Response response = null;

    @Test
    public void create_transaction(){
        response = transactionFlowService.CreateTransaction();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Test(dependsOnMethods = { "create_transaction" })
    public void create_term_sheet(){
        response = transactionFlowService.createTermSheet();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Test(dependsOnMethods = { "create_term_sheet" })
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
    public void create_new_lender() {
        response = transactionFlowService.createNewLenderForCompany();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Test(dependsOnMethods = {"create_new_lender"})
    public void assign_newly_created_lender_to_transaction() {
        response = transactionFlowService.assignNewlyCreatedLenderToTransaction();
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }
}
