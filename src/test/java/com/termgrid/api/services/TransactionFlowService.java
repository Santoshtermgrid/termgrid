package com.termgrid.api.services;

import com.termgrid.api.appApi.clients.assignlendertotransaction.AssignLenderToTransactionAPI;
import com.termgrid.api.appApi.clients.assignlendertotransaction.CreateCompanyByLenderAPI;
import com.termgrid.api.appApi.clients.commons.restassured.StatusCode;
import com.termgrid.api.appApi.clients.commons.utils.DataLoader;
import com.termgrid.api.appApi.clients.commons.utils.FakerUtils;
import com.termgrid.api.appApi.clients.dataroom.AddDataRoomAPI;
import com.termgrid.api.appApi.clients.dataroom.AddFileToDataRoomAPI;
import com.termgrid.api.appApi.clients.element.AddPETransactionAPI;
import com.termgrid.api.appApi.clients.termsheet.CreateTermSheetAPI;
import com.termgrid.api.appApi.clients.users.GetUserDetails;
import com.termgrid.api.appApi.pojos.assignlendertotransaction.AssignLenderToTransPojo;
import com.termgrid.api.appApi.pojos.assignlendertotransaction.CompanyDTOPojo;
import com.termgrid.api.appApi.pojos.assignlendertotransaction.CreateLenderPojo;
import com.termgrid.api.appApi.pojos.dataroom.AddDataRoomPojo;
import com.termgrid.api.appApi.pojos.dataroom.AddDocumentsIntoDataRoomPojo;
import com.termgrid.api.appApi.pojos.dataroom.FileDTOPojo;
import com.termgrid.api.appApi.pojos.dataroom.ParentFileElementPojo;
import com.termgrid.api.appApi.pojos.element.AddPETransactionRequest;
import com.termgrid.api.appApi.pojos.element.IndustryDTOSet;
import com.termgrid.api.appApi.pojos.termsheet.AddTermSheetRequestPojo;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.HashMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransactionFlowService {
    UserFlowContext userFlowContext = new UserFlowContext();
    Response response = null;

    @Step
    public Response CreateTransaction() {
        AddPETransactionRequest payload = AddPETransactionRequest.builder().
                companyId("2")
                .companyToBuy(FakerUtils.generateName())
                .peTransactionTitle(FakerUtils.generateName())
                .targetDate(1640889000000l)
                .industryDTOSet(Arrays.asList(IndustryDTOSet.builder().active(true)
                        .id("10966")
                        .approved(true).name("Chemicals").build())).build();
        response = AddPETransactionAPI.post(payload, DataLoader.getInstance().getUsername(), DataLoader.getInstance().getPassword());
        userFlowContext.setPeTransactionId(response.jsonPath().getString("peTransactionId"));
        return response;
    }

    @Step
    public Response createTermSheet() {
        AddTermSheetRequestPojo payload = AddTermSheetRequestPojo.builder().name(FakerUtils.generateName()).build();
        HashMap<String, String> pathParams = new HashMap<>();
        pathParams.put("transactionid", userFlowContext.getPeTransactionId());
        response = CreateTermSheetAPI.post(payload, pathParams);
        userFlowContext.setTermSheetId(response.jsonPath().getString("id"));
        System.out.println("TermSheet:- " + response.jsonPath().getString("id"));
        return response;
    }

    @Step
    public Response createDataRoom() {
        AddDataRoomPojo payload = AddDataRoomPojo.builder().
                name(FakerUtils.generateName()).isFolder(true).petransactionId(userFlowContext.getPeTransactionId()).fileElementType("PETRANSACTION").build();
        Response response = AddDataRoomAPI.post(payload, DataLoader.getInstance().getUsername(), DataLoader.getInstance().getPassword());
        userFlowContext.setDataRoomID(response.jsonPath().getString("elementId"));
        System.out.println("Data Room ID:- " + response.jsonPath().getString("elementId"));
        return response;
    }


    @Step
    public Response addTheDocumentsIntoDataRoom() {
        response = GetUserDetails.get(DataLoader.getInstance().getUsername(), DataLoader.getInstance().getPassword());
        userFlowContext.setUserID(response.getBody().jsonPath().getString("userId"));
        FileDTOPojo fdto = FileDTOPojo.builder().createdBy(userFlowContext.getUserID()).fileName("QA Think.png").fileURL("http://s3.amazonaws.com/testtermgrid/57c537eb-acfd-4a8b-b5d9-bdc2992c9abf").
                bucketName("testtermgrid").objectKey("57c537eb-acfd-4a8b-b5d9-bdc2992c9abf").byteSize("69702").contentType("image/png").build();
        ParentFileElementPojo pfe = ParentFileElementPojo.builder().elementId(userFlowContext.getDataRoomID()).build();
        AddDocumentsIntoDataRoomPojo payload = AddDocumentsIntoDataRoomPojo.builder().name("QA Think.png").isFolder(false).fileURL("http://s3.amazonaws.com/testtermgrid/57c537eb-acfd-4a8b-b5d9-bdc2992c9abf")
                .petransactionId(userFlowContext.getPeTransactionId()).fileElementType("PETRANSACTION").fileDTO(fdto).parentFileElement(pfe).build();
        return response = AddFileToDataRoomAPI.post(payload);
    }

    @Step
    public Response createNewLenderForCompany() {
        CreateLenderPojo payload = CreateLenderPojo.builder().name(FakerUtils.generateName()).build();
        Response response = CreateCompanyByLenderAPI.post(payload,DataLoader.getInstance().getUsername(), DataLoader.getInstance().getPassword());
        userFlowContext.setLenderID(response.getBody().jsonPath().getString("id"));
        userFlowContext.setLenderName(response.getBody().jsonPath().getString("name"));
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        System.out.println("New Lender:- "+ userFlowContext.getLenderID());
        System.out.println("New Lender:- "+ userFlowContext.getLenderName());
        return response;
    }

    @Step
    public Response assignNewlyCreatedLenderToTransaction() {
        CompanyDTOPojo cDTO = CompanyDTOPojo.builder().createdBy(userFlowContext.getUserID()).id(userFlowContext.getLenderID()).name(userFlowContext.getLenderName()).tncApproved(true).payment(false).build();
        AssignLenderToTransPojo payload = AssignLenderToTransPojo.builder().privateEquityTransactionId(userFlowContext.getPeTransactionId()).companyDTO(cDTO).build();
        return response = AssignLenderToTransactionAPI.post(payload);
    }

}