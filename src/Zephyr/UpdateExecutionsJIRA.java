package Zephyr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateExecutionsJIRA {


    public static void updateExecutionInBulk(Map<String, List<String>> allExecutionIds,Map<String, List<String>> executionDetails) throws JSONException, URISyntaxException, IOException {
        Map<String, List<String>> failedTCExecutionIDStepComment = new HashMap<String, List<String>>();
        List <String> passedTCExecutionIDs = new ArrayList<String>();

        for (String key : executionDetails.keySet()) {

            List<String> executionDetail = executionDetails.get(key);
            List<String>tempExecutionIds = allExecutionIds.get(key);

            if(executionDetail.get(0).equalsIgnoreCase("Passed")) {
                passedTCExecutionIDs.add(tempExecutionIds.get(0));
            }

            else if(executionDetail.get(0).equalsIgnoreCase("Failed")){
                failedTCExecutionIDStepComment.put(key, executionDetail);
            }

            executionDetail = null;
            tempExecutionIds = null;

        }

        String updateExecutionUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.update_Executions_InBulk;
        JSONObject passedTestsObj = new JSONObject();
        passedTestsObj.put("executions" , passedTCExecutionIDs.toArray(new String[passedTCExecutionIDs.size()]));
        passedTestsObj.put("status", 1);

        if(!passedTCExecutionIDs.isEmpty()) {
            String passedTCresponse= RequestMethod.sendPUT(updateExecutionUri, passedTestsObj);}
        else{
            System.out.println("No Passed TC to update");}


        if(!(failedTCExecutionIDStepComment.isEmpty()))
            updateExecutionIndividual(allExecutionIds,failedTCExecutionIDStepComment);
        else {
            System.out.println("No Failed TC to update");}



    }



    private static void updateExecutionIndividual(Map<String, List<String>> allExecutionIds,Map<String, List<String>> failedTCExecutionIDStepComment) throws JSONException, URISyntaxException, IOException {
        List<String> executionIDIssueID;
        List<String> statusStepComment;
        String updateTestUri;
        String updatestepUri;

        for (String key : failedTCExecutionIDStepComment.keySet()){

            executionIDIssueID =  allExecutionIds.get(key);
            statusStepComment =  failedTCExecutionIDStepComment.get(key);

            if(statusStepComment.get(1) == "" && statusStepComment.get(2) == ""){
            // bulk update for failed TC
                updateTestUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.update_executions + executionIDIssueID.get(0) + "/execute";
                System.out.println("executionIDIssueID: " + executionIDIssueID.get(0));
                JSONObject updateTestJson = new JSONObject();
                updateTestJson.put("status", "2");
                updateTestJson.put("comment", "");
                RequestMethod.sendPUT(updateTestUri, updateTestJson);
                updateTestJson = null;
            }

            else if(statusStepComment.get(1) == "" && statusStepComment.get(2) != "") {
                //Individual TC update with comment
                updateTestUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.update_executions + executionIDIssueID.get(0) + "/execute";
                JSONObject updateTestJson = new JSONObject();
                updateTestJson.put("status", "2");
                updateTestJson.put("comment", statusStepComment.get(2));
                RequestMethod.sendPUT(updateTestUri, updateTestJson);
                updateTestJson = null;

            }

            else if(statusStepComment.get(1) != "" && statusStepComment.get(2) != "") {

                updatestepUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.get_Step_Results_by_ExecutionID + executionIDIssueID.get(0);
                updateByEachStep(updatestepUri, executionIDIssueID.get(0), executionIDIssueID.get(1), statusStepComment.get(1));
                updateTestUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.update_executions + executionIDIssueID.get(0) + "/execute";
                JSONObject updateTestJson = new JSONObject();
                updateTestJson.put("status", "2");
                updateTestJson.put("comment", statusStepComment.get(2));
                RequestMethod.sendPUT(updateTestUri, updateTestJson);
                updateTestJson = null;

            }

            executionIDIssueID = null;
            statusStepComment = null;

        }

    }

    private static void updateByEachStep(String getStepResultUrl, String executionID,String IssueID, String step) throws UnsupportedEncodingException, URISyntaxException {

        String stepupdateUrl = JiraConstatnts.jiraBaseURL + JiraConstatnts.update_Step_Individual;

        String response= RequestMethod.sendGET(getStepResultUrl);
        JSONArray allIssues = new JSONArray(response);

        int stepfailed = Integer.parseInt(step);
        for (int j = 1; j <= stepfailed; j++) {
            if(j<stepfailed) {
                JSONObject jobj = allIssues.getJSONObject(j-1);
                long stepID = jobj.getLong("stepId");
                long stepResultID = jobj.getLong("id");
                String url = stepupdateUrl + stepResultID;

                JSONObject jobj2 = new JSONObject();
                jobj2.put("executionId", Long.parseLong(executionID));
                jobj2.put("stepId", stepID);
                jobj2.put("id", stepResultID);
                jobj2.put("status", "1");

                String r = RequestMethod.sendPUT(url, jobj2);

            }
            else {
                JSONObject jobj = allIssues.getJSONObject(j-1);
                long stepID = jobj.getLong("stepId");
                long stepResultID = jobj.getLong("id");
                String url = stepupdateUrl + stepResultID;

                JSONObject jobj2 = new JSONObject();
                jobj2.put("status", "2");
                jobj2.put("stepId", stepID);
                jobj2.put("executionId", Long.parseLong(executionID));
                jobj2.put("id", stepResultID);


                String r = RequestMethod.sendPUT(url, jobj2);
            }



        }

    }


}
