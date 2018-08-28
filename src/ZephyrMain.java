import Zephyr.JiraUpdate;
import Zephyr.TestsResults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZephyrMain {
    static Map<String, List<String>> executionDetails = new HashMap<String, List<String>>();


    public static void main(String args[]) throws Exception {
        executionDetails= TestsResults.getExecutiondetailsfromAutomation();
        JiraUpdate j = new JiraUpdate();
        j.updateToJira("10000", "VertexAll", "SmokeTests", "TEST", executionDetails);


    }





}
