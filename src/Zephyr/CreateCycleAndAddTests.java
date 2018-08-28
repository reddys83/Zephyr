package Zephyr;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import java.util.*;


import org.json.JSONException;
import org.json.JSONObject;


public final class CreateCycleAndAddTests {

    public static String getCycleId(String cycleName, String projectId, String version) throws  URISyntaxException{
        String cycleID = "-1";
        String url = JiraConstatnts.jiraBaseURL + JiraConstatnts.get_CycleID + "?projectId=" + projectId + "&versionId=" + JiraUpdate.projectVersionIDs.get(version);
        String response = RequestMethod.sendGET(url);
        JSONObject j = new JSONObject(response);
        String t, currentKey;
        JSONObject temp;

        Iterator<String> iterator = j.keys();
            while (iterator.hasNext()) {
               currentKey = iterator.next();
               if(!(currentKey.equalsIgnoreCase("recordsCount"))){
                temp = j.getJSONObject(currentKey);
                t = temp.getString("name");
                Boolean b = cycleName.equalsIgnoreCase(t);

                if(b)
                {
                    cycleID = currentKey;
                    break;
                }

               }
                 t= null;
                 currentKey = null;
                 temp = null;

            }

            return cycleID;

    }


    public static String createCycleSetup(String cycleName, String cycleDescription, String environment, String projectId, String version) throws JSONException, URISyntaxException, UnsupportedEncodingException {

        String cycleID;


        String createCycleUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.create_Cycle ;
        JSONObject createCycleObj = new JSONObject();
        createCycleObj.put("name", cycleName);
        createCycleObj.put("environment", environment);
        createCycleObj.put("description", cycleDescription);
        createCycleObj.put("projectId", projectId);
        createCycleObj.put("versionId", JiraUpdate.projectVersionIDs.get(version));

        String createCycleResponse = RequestMethod.sendPost(createCycleUri, createCycleObj);
        cycleID = parseCreateCycleResponse(createCycleResponse);
        return cycleID;

    }



    private static String parseCreateCycleResponse(String response) {
        String cycleId = "-1";;
        try {
            JSONObject cycleObj = new JSONObject(response);
            cycleId = cycleObj.getString("id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cycleId;
    }


    static void addTestSetup(String cycleID, Map<String, List<String>>executionDetail,Map<String, String> tcToKeyMap, String projectId, String version, String folderID) throws JSONException, IllegalStateException, URISyntaxException, IOException {

        Set<String> keys = executionDetail.keySet();
        List<String>list = new ArrayList<String>();
        for(String key: keys){
            list.add(tcToKeyMap.get(key));
        }


        String addTestsUri = JiraConstatnts.jiraBaseURL + JiraConstatnts.addTestsUri ;
        JSONObject addTestsObj = new JSONObject();
        addTestsObj.put("issues", list.toArray(new String[list.size()]));
        addTestsObj.put("versionId",  Long.parseLong(JiraUpdate.projectVersionIDs.get(version)));
        addTestsObj.put("projectId",  Long.parseLong(projectId));
        addTestsObj.put("cycleId", cycleID);
        addTestsObj.put("folderId", Long.parseLong(folderID));
        addTestsObj.put("method", "1");

        String response = RequestMethod.sendPost(addTestsUri, addTestsObj);
        if (response !=null) System.out.println("tests added successfully");

    }

}