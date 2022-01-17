package com.termgrid.api.tests;

import com.termgrid.api.appApi.clients.addfiletodataroom.AddFileToDataRoomAPI;
import com.termgrid.api.appApi.clients.assignlender.AssignLenderToTransactionAPI;
import com.termgrid.api.appApi.clients.dataroom.AddDataRoomAPI;
import com.termgrid.api.appApi.clients.element.AddPETransactionAPI;
import com.termgrid.api.appApi.clients.lender.CreateCompanyByLenderAPI;
import com.termgrid.api.appApi.clients.termsheet.CreateTermSheetAPI;
import com.termgrid.api.appApi.clients.user.GetUserAPI;
import com.termgrid.api.appApi.pojo.addFileToDataRoom.AddDocumentsIntoDataRoomPojo;
import com.termgrid.api.appApi.pojo.addFileToDataRoom.FileDTOPojo;
import com.termgrid.api.appApi.pojo.addFileToDataRoom.ParentFileElementPojo;
import com.termgrid.api.appApi.pojo.addTermSheet.AddTermSheetPojo;
import com.termgrid.api.appApi.pojo.assignlendertotrans.AssignLenderToTransPojo;
import com.termgrid.api.appApi.pojo.assignlendertotrans.CompanyDTOPojo;
import com.termgrid.api.appApi.pojo.assignlendertotrans.CreateLenderPojo;
import com.termgrid.api.appApi.pojo.dataroom.AddDataRoomPojo;
import com.termgrid.api.appApi.pojo.element.AddPETransactionRequest;
import com.termgrid.api.appApi.pojo.element.IndustryDTOSet;
import com.termgrid.api.commons.StatusCode;
import com.termgrid.api.commons.utils.DataLoader;
import com.termgrid.api.commons.utils.FakerUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONException;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Add Transaction Flow")
@Feature("")
public class TestFlow {

    String transactionId=null;
    String transactionName=FakerUtils.generateName();
    String targetCompany=FakerUtils.generateName();
    String termSheetId=null;
    String industry = "Healthcare";
    String dataRoomID=null;
    String dataRoomName=null;
    String newLender = FakerUtils.generateName();
    String newTermSheet = FakerUtils.generateName();
    String newDataRoom = FakerUtils.generateName();
    String userId=null;
    boolean result;
    String lenderID=null;

    @Story("TermGrid AddTransaction API")
    @Description("should be able to create a peTransaction")
    @Test(description = "should be able to create a peTransaction")
    public void ShouldBeAbleToCreateTransaction() {
        AddPETransactionRequest payload = AddPETransactionRequest.builder().
                companyId("2")
                .companyToBuy(targetCompany)
                .peTransactionTitle(transactionName)
                .targetDate(1640889000000l)
                .industryDTOSet(Arrays.asList(IndustryDTOSet.builder().active(true)
                        .id("10971")
                        .approved(true).name(industry).build())).build();

        Response response = AddPETransactionAPI.post(payload, DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword());

        transactionId=response.jsonPath().getString("peTransactionId");

        System.out.println("TransactionId==="+response.jsonPath().getString("peTransactionId"));

        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Story("TermGrid Add Term Sheet API")
    @Description("AddTheTermSheetAfterAddingTransaction")
    @Test(description = "Check with the transaction id can able to add term sheet", dependsOnMethods = { "GetTheUserID" })
    public void AddTheTermSheet() throws InterruptedException {
        AddTermSheetPojo payload = AddTermSheetPojo.builder().name(newTermSheet).build();
        HashMap<String, String> pathParams= new HashMap<>();
        pathParams.put("transactionid",transactionId);
        Response response = CreateTermSheetAPI.post(payload ,pathParams);
        termSheetId=response.jsonPath().getString("id");
        System.out.println("ID for TermSHeet:- " + termSheetId);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Story("TermGrid Data Room API")
    @Description("AddTheDataRoomAfterAddingTransaction")
    @Test(description = "Check with the transaction id can able to add Data Room", dependsOnMethods = { "AddTheTermSheet" })
    public void AddTheDataRoom() throws InterruptedException {
        AddDataRoomPojo payload = AddDataRoomPojo.builder().
                name(newDataRoom).isFolder(true).petransactionId(transactionId).fileElementType("PETRANSACTION").build();
        Response response = AddDataRoomAPI.post(payload, DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword());
        dataRoomID=response.jsonPath().getString("elementId");
        dataRoomName=response.jsonPath().getString("name");
        System.out.println("ID for Data Room:- " + dataRoomID);
        System.out.println("ID for Data Room Name:- " + dataRoomName);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Story("TermGrid Upload Documents in to the Data Room")
    @Description("AddTheDocumentsIntoTheDataRoom")
    @Test(description = "Check png/image etc Documents can able to add into Data Room", dependsOnMethods = { "AddTheDataRoom" })
    public void AddTheDocumentsIntoDataRoom() throws InterruptedException {
        FileDTOPojo fdto = FileDTOPojo.builder().createdBy(userId).fileName("QA Think.png").fileURL("http://s3.amazonaws.com/testtermgrid/57c537eb-acfd-4a8b-b5d9-bdc2992c9abf").
                bucketName("testtermgrid").objectKey("57c537eb-acfd-4a8b-b5d9-bdc2992c9abf").byteSize("69702").contentType("image/png").build();
        ParentFileElementPojo pfe = ParentFileElementPojo.builder().elementId("4547586130165367869").build();
        AddDocumentsIntoDataRoomPojo payload = AddDocumentsIntoDataRoomPojo.builder().name("QA Think.png").isFolder(false).fileURL("http://s3.amazonaws.com/testtermgrid/57c537eb-acfd-4a8b-b5d9-bdc2992c9abf")
                .petransactionId(transactionId).fileElementType("PETRANSACTION").fileDTO(fdto).parentFileElement(pfe).build();
        Response response = AddFileToDataRoomAPI.post(payload);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        //AddFileResponsePojo obj = (AddFileResponsePojo) (((RestAssuredResponseImpl) response).getGroovyResponse().getContent());
        // Object obj = ((RestAssuredResponseImpl) response).getGroovyResponse().getContent();
        // AddFileResponsePojo responseObject = new ObjectMapper().readValue(((RestAssuredResponseImpl) response).getGroovyResponse().getContent(), AddFileResponsePojo.class);
        // dataRoomID=response.jsonPath().getString("elementId");
        // dataRoomName=response.jsonPath().getString("name");
        // System.out.println("ID for Data Room:- " + dataRoomID);
        // System.out.println("ID for Data Room Name:- " + dataRoomName);
        //AddFileResponsePojo responseObject = new ObjectMapper().readValue(response.groovyResponse.content, AddFileResponsePojo.class);
    }

    @Story("TermGrid Create new Lender")
    @Description("CheckUserCanCreateNewLenderForPerformingTest")
    @Test(description = "Create new lender in the company",dependsOnMethods = { "AddTheTermSheet" })
    public void CreateNewLenderForCompany() throws InterruptedException {
        CreateLenderPojo payload = CreateLenderPojo.builder().name(newLender).build();
        Response response = CreateCompanyByLenderAPI.post(payload,DataLoader.getInstance().getUsername(), DataLoader.getInstance().getPassword());
        result = response.getBody().jsonPath().getString("name").equals(newLender);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        assertThat(result, equalTo(true));
        lenderID = response.getBody().jsonPath().getString("id");
        System.out.println("New Lender:- "+ newLender);
    }

    @Story("TermGrid get User ID by which transaction is being created")
    @Description("GetTheUserID")
      @Test(description = "Create new lender in the company",dependsOnMethods = { "ShouldBeAbleToCreateTransaction" })
    public void GetTheUserID() throws InterruptedException {
        Response response = GetUserAPI.get(DataLoader.getInstance().getUsername(), DataLoader.getInstance().getPassword());
        userId = response.getBody().jsonPath().getString("userId");
        System.out.println("User ID:- "+ userId);
    }

    @Story("TermGrid Assign newly created Lender to transaction")
    @Description("CheckUserCanAssignNewlyCreatedLenderToTransaction")
    //@Test(description = "Assign newly created lender to transaction", dependsOnMethods = { "CreateNewLenderForCompany" })
    @Test(description = "Assign newly created lender to transaction")
    public void AssignNewlyCreatedLenderToTransaction() throws InterruptedException, IOException, JSONException {
        CompanyDTOPojo cDTO = CompanyDTOPojo.builder().createdBy(userId).id(lenderID).name(newLender).tncApproved(true).payment(false).build();
        AssignLenderToTransPojo payload = AssignLenderToTransPojo.builder().privateEquityTransactionId(transactionId).companyDTO(cDTO).build();
       Response response = AssignLenderToTransactionAPI.post(payload);
      assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }


}
