package com.termgrid.api.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.termgrid.api.appApi.clients.eventUnderDeal.CreateEventAPI;
import com.termgrid.api.appApi.pojo.createEvent.CreateEventRequestPojo;
import com.termgrid.api.appApi.pojo.createEvent.CreateLenderDTOPojo;
import com.termgrid.api.appApi.pojo.deleteEvent.DeleteEventRequestPojo;
import com.termgrid.api.appApi.pojo.deleteEvent.DeleteLenderDTOPojo;
import com.termgrid.api.commons.StatusCode;
import com.termgrid.api.commons.utils.DataLoader;
import com.termgrid.api.commons.utils.FakerUtils;
import io.qameta.allure.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Termgrid Deal Team")
@Feature("TermGrid Deal Team login flow e2e")
public class DealTeamE2ETest {
    Response createResponse = null;
    @Story("TermGrid Deal team event creation")
    @Description("should be able to create a event")
    @Test(description = "should be able to create a event title")
    public void ShouldBeAbleToCreateAEventTitle() throws JsonProcessingException {
        CreateLenderDTOPojo createLenDTO = CreateLenderDTOPojo.builder().id("4528311781835146842").name("HDFC Bank").build();
        ArrayList<CreateLenderDTOPojo> objects = new ArrayList<>();
        objects.add(createLenDTO);
        CreateEventRequestPojo payload = CreateEventRequestPojo.builder().title(FakerUtils.generateName()).
                targetDate(1640889000000l).note("Create new Events").sendEmail(true).peTransactionId("4521790190263469966").lenderDTOS(objects).build();

        createResponse = CreateEventAPI.post(payload, DataLoader.getInstance().getUsername(), DataLoader.getInstance().getPassword());

        String title = createResponse.jsonPath().getString("title");
        assertThat(createResponse.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
       // assertThat("New Event on Today", equalTo(title));
    }
        @Story("TermGrid Deal team event deletion")
        @Description("should be able to delete a event")
        @Test(description = "should be able to delete a event title")
        public void ShouldBeAbleToDeleteAEventTitle() {
           /* DeleteLenderDTOPojo deleteLenDTOPojo= DeleteLenderDTOPojo.builder().id("4528311781835146842").name("HDFC Bank").build();
            ArrayList<DeleteLenderDTOPojo> objects = new ArrayList<>();
            objects = new ArrayList<>();
            objects.add(deleteLenDTOPojo);
            DeleteEventRequestPojo payload = DeleteEventRequestPojo.builder().title("New Event on Today").
                    targetDate(1640889000000l).note("Create new Events").sendEmail(true).peTransactionId("4521790190263469966").lenderDTOS(objects).build();*/



            Response response = CreateEventAPI.deleteUsingResponse(createResponse, DataLoader.getInstance().getUsername(),DataLoader.getInstance().getPassword());
           // String title=response.jsonPath().getString("title");
            assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
           // assertThat("New Event on Today", equalTo(title));






    }
}
