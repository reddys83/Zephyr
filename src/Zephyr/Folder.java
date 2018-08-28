package Zephyr;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Folder {
 public static String folderID= "-1";

    public static String checkFolderExists(String cycleID,String projectId,String version) throws URISyntaxException, UnsupportedEncodingException{
        String folderID =  "-1";
        SimpleDateFormat format = new SimpleDateFormat(JiraConstatnts.pattern);
        String folderName = format.format(new Date());
        String url = JiraConstatnts.jiraBaseURL + JiraConstatnts.get_List_Of_Folders + cycleID + "/folders" + "?projectId=" + projectId + "&versionId=" + JiraUpdate.projectVersionIDs.get(version) + "&offset=0";
        String response = RequestMethod.sendGET(url);

        JSONArray j = new JSONArray(response);
        for(int i = 0 ; i < j.length(); i++){
            JSONObject obj = j.getJSONObject(i);
             if(obj.getString("folderName").equalsIgnoreCase(folderName)){
                 folderID = Long.toString(obj.getLong("folderId"));
                 break;
             }
        }
    return folderID;
    }




    public static String createFolderIntoCycle(String cycleID, String projectID, String version, String environment) throws URISyntaxException, UnsupportedEncodingException {
        SimpleDateFormat format = new SimpleDateFormat(JiraConstatnts.pattern);

        String url = JiraConstatnts.jiraBaseURL + JiraConstatnts.create_Folder_Into_Cycle;
        JSONObject createFolderObj = new JSONObject();
        createFolderObj.put("cycleId", Long.parseLong(cycleID));
        createFolderObj.put("name",  format.format(new Date()));
        createFolderObj.put("description", "contains execution results of" + format.format(new Date()) + "in" + environment);
        createFolderObj.put("projectId", Long.parseLong(projectID));
        createFolderObj.put("versionId", Long.parseLong(JiraUpdate.projectVersionIDs.get(version)));

        String response = RequestMethod.sendPost(url, createFolderObj);
        parseCreateFolderResponse(response);
        return folderID;

    }

private static void parseCreateFolderResponse(String response){
    JSONObject j = new JSONObject(response);
    folderID= String.valueOf(j.getLong("id"));

}



}
