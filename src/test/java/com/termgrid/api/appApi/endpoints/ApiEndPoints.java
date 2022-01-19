package com.termgrid.api.appApi.endpoints;

public class ApiEndPoints {
    public static final String deleteTransactionUrl="api/rest/element/deletePETransaction/{transactionid}";
    public static final String addPETransactionUrl="api/rest/element/addPETransaction";
    public static final String getPETransactionUrl="api/rest/element/getPETransaction/{transactionid}";
    public static final String createCompanyUrl="api/rest/company";
    public static final String registerLoginUrl="api/rest/users/register";
    public static final String customerLoginUrl="api/rest/users/customLogin";
    public  static  final String verfiyEmailUrl="api/rest/admin/dashboard/verifyUserEmail/{emailId}";
    public static final String addCompanyUrl="/api/rest/company";
    public static final String getUserTeamUrl="/api/rest/team";
    public static final String inviteUserToTeamUrl="api/rest/invite";
    public static final String createJoinLinkUrl="api/rest/admin/dashboard/createJoinLink/";
    public  static final String updateTransactionUrl="/api/rest/admin/dashboard/updateTeamMemberTransactionRights";
    public  static  final String getUserUrl="api/rest/users/getUser";

    public static final String addFolderElementUrl = "api/rest/element/addFolderElement";
    public static final String addFileToDataRoomUrl = "api/rest/fileelement/addFile";
    public static final String createLenderUnderCompanyUrl = "/api/rest/company/byLender";
    public static final String assignLenderToTransactionUrl = "/api/rest/lenders";
    public static final String createTermSheetUrl = "api/rest/new-webform/createWebFormConfig/{transactionid}";
    public static final String createEventUrl = "/api/rest/deadline/create";
    public static final String deletedCreatedEventUrl = "/api/rest/deadline/delete";

}
