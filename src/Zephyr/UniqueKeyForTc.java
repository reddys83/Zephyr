package Zephyr;

import java.io.IOException;

import java.net.URISyntaxException;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class UniqueKeyForTc {

    static Map<String, String> tcToKeyMap = new HashMap<String, String>();
    public static Map<String, String> getUniqueKeyForTc(String project) throws JSONException, IOException, URISyntaxException {

        int totalCount;
        int timesofcall;
        String jiraSearchurl = JiraConstatnts.jiraBaseURL + JiraConstatnts.jira_search;
        JSONObject searchJsonObj = new JSONObject();
        String jql =  "project ="+project+" and issueType = Test";
        searchJsonObj.put("jql",jql);
        searchJsonObj.put("startAt", 0);
        searchJsonObj.put("maxResults", 50);
        String response = RequestMethod.sendPost(jiraSearchurl, searchJsonObj);
        JSONObject allIssues = new JSONObject(response);
        totalCount = allIssues.getInt("total");

        if(totalCount <= 50) {
            int defstart =0;
            getFiftyDetails(jiraSearchurl,defstart,jql);

        }

        else {
            if(totalCount % 50 == 0) {
                timesofcall = totalCount/50;
                for(int i = 0 ; i < timesofcall ; i++ ) {
                    int startAt =  50*i;
                    getFiftyDetails(jiraSearchurl,startAt,jql);

                }
            }

            else {
                float temp = totalCount/50;
                timesofcall = (int)temp + 1;
                for(int i = 0 ; i < timesofcall ; i++ ) {
                    int startAt =  50*i;
                    getFiftyDetails(jiraSearchurl,startAt,jql);
                }

            }
        }

        return tcToKeyMap;
    }

    private static void getFiftyDetails(String url, int startAt, String jql) throws IOException, URISyntaxException {
        JSONObject obj = new JSONObject();
        obj.put("jql", jql);
        obj.put("startAt", startAt);
        obj.put("maxResults", 50);

        String response = RequestMethod.sendPost(url, obj);
        JSONObject allIssues = new JSONObject(response);
        JSONArray IssuesArray = allIssues.getJSONArray("issues");
        for (int j = 0; j <= IssuesArray.length() - 1; j++) {
            JSONObject jobj = IssuesArray.getJSONObject(j);
            String key = jobj.getString("key");
            JSONObject jobj2 = jobj.getJSONObject("fields");
            String name = jobj2.getString("summary");
            tcToKeyMap.put(name,key);
        }

    }

}