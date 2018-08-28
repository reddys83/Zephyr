package Zephyr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class ProjectVersion {
    public static Map<String, String> versionIDs = new HashMap<String,String>();
    public static Map<String, String> getAllProjectVersion(String projrctId)throws JSONException, URISyntaxException {
        String url = JiraConstatnts.jiraBaseURL + JiraConstatnts.jira_project_version + projrctId + "/versions";
        String response = RequestMethod.sendGET(url);
        parseAllProjectVersionResponse(response);

        return versionIDs;
    }

    private static void parseAllProjectVersionResponse(String response){
        JSONArray jobj = new JSONArray(response);
        for(int i=0; i<jobj.length(); i++){
            JSONObject j = jobj.getJSONObject(i);
            versionIDs.put(j.getString("name"), j.getString("id"));
        }

    }


}
