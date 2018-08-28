package Zephyr;

import java.util.Base64;

//import com.sun.jersey.core.util.Base64;

public class JiraConstatnts {

    //Cycle:creates new cycle
    static String create_Cycle = "/rest/zapi/latest/cycle";
    //Execution: add tests to cycle
    static String addTestsUri = "/rest/zapi/latest/execution/addTestsToCycle/";
    //Execution: Get List of Executions by cycle ID, version ID, Project ID
    static String get_executions = "/rest/zapi/latest/execution?";
    //Execution: Update Execution
    static String update_executions = "/rest/zapi/latest/execution/";
    static String update_Executions_InBulk = "/rest/zapi/latest/execution/updateBulkStatus";
    static String get_Step_Results_by_ExecutionID = "/rest/zapi/latest/stepResult?executionId=";
    static String update_Step_Individual = "/rest/zapi/latest/stepResult/";
    static String create_filter = "/rest/zapi/latest/zql/executionFilter";
    static String jira_search = "/rest/api/2/search";
    static String jira_project_version = "/rest/api/2/project/";
    static String create_Folder_Into_Cycle = "/rest/zapi/latest/folder/create";
    static String get_CycleID = "/rest/zapi/latest/cycle";
    static  String get_List_Of_Folders = "/rest/zapi/latest/cycle/";


    /** Declare JIRA,Zephyr URL,access and secret Keys -- should be moved to any property file, should be secure*/

    // JIRA Cloud URL of the instance
    static String jiraBaseURL = "http://automation-qa:8080";
    // Replace zephyr baseurl <ZAPI_Cloud_URL> shared with the user for ZAPI Cloud Installation
    static String pattern = "MMddyyyy";



    /** Declare parameter values here, need to male dynamic during implementation */
    //public static String userName = "XXXX";
    //static String password = "XXXX";
    public static String projectId = "10000";
    public static String jqlAllTcOfProject = "rest/api/2/search?jql=project%20=%20SAM%20and%20issueType%20=%20Test&fields=summary";


    //static String feedForencode = 	userName + ":" + password;
    //static String auth = Base64.getEncoder().encodeToString(feedForencode.getBytes());
    static String auth = "U1BhZG1pbjpTUGFkbWlu";


}
