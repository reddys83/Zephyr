package Zephyr;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class CreateStepID {

    public static void createStepIDbyExecutionID(Map<String, List<String>> allExecutionIds,Map<String, List<String>> executionDetails) throws URISyntaxException {
        String url = JiraConstatnts.jiraBaseURL + JiraConstatnts.update_executions;

        for (String key : executionDetails.keySet()) {
            List<String>tempExecutionIds = allExecutionIds.get(key);
            String uriStr = url + tempExecutionIds.get(0) + "?expand=checksteps";
            RequestMethod.sendGET(uriStr);
            tempExecutionIds = null;

        }

    }

}