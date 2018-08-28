package Zephyr;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JiraUpdate {

    static Map<String, List<String>> executionIds = new HashMap<String, List<String>>();
    static Map<String, String> tcToKeyMap = new HashMap<String, String>();
    static Map<String, String> projectVersionIDs = new HashMap<>();
    static String folderID;
    static String cycleID;
    public void updateToJira(String projectID, String versionOrDomain, String cycleName, String environment, Map<String, List<String>> executionDetails )throws URISyntaxException, IOException,InterruptedException {

        cycleName =  environment + "_" + cycleName;
        projectVersionIDs = ProjectVersion.getAllProjectVersion(projectID);
        tcToKeyMap = UniqueKeyForTc.getUniqueKeyForTc(projectID);
        cycleID = CreateCycleAndAddTests.getCycleId(cycleName, projectID, versionOrDomain);
        folderID = Folder.checkFolderExists(cycleID, projectID, versionOrDomain);

        if (folderID.equalsIgnoreCase("-1")) {
            folderID = Folder.createFolderIntoCycle(cycleID, projectID, versionOrDomain,environment);
            CreateCycleAndAddTests.addTestSetup(cycleID, executionDetails, tcToKeyMap, projectID, versionOrDomain, folderID);
            Thread.sleep(1000);
        }        cycleID = CreateCycleAndAddTests.getCycleId(cycleName, projectID, versionOrDomain);


        executionIds = FetchAndExecute.getExecutionsSetup(cycleID, projectID, versionOrDomain, folderID);
        CreateStepID.createStepIDbyExecutionID(executionIds, executionDetails);
        UpdateExecutionsJIRA.updateExecutionInBulk(executionIds, executionDetails);

    }

}
