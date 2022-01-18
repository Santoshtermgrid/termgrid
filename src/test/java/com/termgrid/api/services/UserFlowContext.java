package com.termgrid.api.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFlowContext {

        String user1Emailid;
        String user1UserId;
        String companyId;
        String teamMember1EmailId;
        String teamMember1UserId;
        String teamMember1invitationStatus;
        String adminJoinLinkUrl;
        Boolean teamMember1createTransactionStatus;
        Boolean user1createTransactionStatus;
        String industriesId;
        String industryName;
        String peTransactionId;
        String termSheetId;
        String dataRoomID;
        String lenderID;
        String lenderName;
        String userID;



}
