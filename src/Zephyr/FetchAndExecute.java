package Zephyr;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FetchAndExecute {
    public static Map<String, List<String>> AllExecutionIdsIssueID =  new HashMap<String, List<String>>();


    static Map<String, List<String>> getExecutionsSetup(String cycleID, String project, String version, String folderID) throws JSONException, URISyntaxException {

        String getExecutionsUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.get_executions + "projectId=" + project + "&versionId=" + JiraUpdate.projectVersionIDs.get(version) + "&cycleId=" + cycleID + "&folderId=" + folderID;
        String response = RequestMethod.sendGET(getExecutionsUri + "&offset=0");
        JSONObject allIssues = new JSONObject(response);
        int totalCount = allIssues.getInt("recordsCount");
        if(totalCount <= 10) {
            parseGetPageExecution(getExecutionsUri + "&offset=0");

        }

        else {
            if(totalCount % 10 == 0) {
                int timesofcall = totalCount/10;
                for(int i = 0 ; i < timesofcall ; i++ ) {
                    String offsetURI = getExecutionsUri + "&offset=" + 10*i ;

                    parseGetPageExecution(offsetURI);

                }
            }

            else {
                float temp = totalCount/10;
                int timesofcall = (int)temp + 1;
                System.out.println("Times of Call: " + timesofcall);

                for(int i = 0 ; i < timesofcall ; i++ ) {
                    String offsetURI = getExecutionsUri + "&offset=" + 10*i  ;

                    parseGetPageExecution(offsetURI);

                }

            }
        }

        return AllExecutionIdsIssueID;

    }


    private static void parseGetPageExecution(String uriStr) throws URISyntaxException, JSONException {

        String response = RequestMethod.sendGET(uriStr);
        JSONObject allIssues = new JSONObject(response);
        JSONArray IssuesArray = allIssues.getJSONArray("executions");

        for (int j = 0; j <= IssuesArray.length() - 1; j++) {
            JSONObject jobj = IssuesArray.getJSONObject(j);
            long execution_ID = jobj.getLong("id");
            String issueSummary = jobj.getString("summary");
            long issueId = jobj.getLong("issueId");
            AllExecutionIdsIssueID.put(issueSummary,Arrays.asList(String.valueOf(execution_ID), String.valueOf(issueId)));
        }


    }




}
