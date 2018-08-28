package Zephyr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestsResults {
    public static Map<String, List<String>> getExecutiondetailsfromAutomation() {
        Map<String, List<String>> executionDetail = new HashMap<String, List<String>>();

      executionDetail.put("Reg1", Arrays.asList("Failed", "1", "TC execution level comment"));
      executionDetail.put("Reg3", Arrays.asList("Failed", "", ""));
      executionDetail.put("Reg4", Arrays.asList("Failed", "", "TC execution level comment"));

      /*        executionDetail.put("Reg6", Arrays.asList("Failed", "2", "Reg6 Failed Reason"));executionDetail.put("Reg7", Arrays.asList("Passed" ));executionDetail.put("Reg8", Arrays.asList("Passed"));executionDetail.put("Reg9", Arrays.asList("Passed"));executionDetail.put("Reg10", Arrays.asList("Passed"));
        executionDetail.put("Reg11", Arrays.asList("Failed", "3", "Reg11 Failed Reason"));executionDetail.put("Reg12", Arrays.asList("Passed" ));executionDetail.put("Reg13", Arrays.asList("Passed"));executionDetail.put("Reg14", Arrays.asList("Passed"));executionDetail.put("Reg15", Arrays.asList("Passed"));
        executionDetail.put("Reg16", Arrays.asList("Passed", "4", "Reg16 Failed Reason"));executionDetail.put("Reg17", Arrays.asList("Passed" ));executionDetail.put("Reg18", Arrays.asList("Passed"));executionDetail.put("Reg19", Arrays.asList("Passed"));executionDetail.put("Reg20", Arrays.asList("Passed"));
        executionDetail.put("Reg21", Arrays.asList("Passed"));executionDetail.put("Reg22", Arrays.asList("Passed" ));executionDetail.put("Reg23", Arrays.asList("Passed"));executionDetail.put("Reg24", Arrays.asList("Passed"));executionDetail.put("Reg25", Arrays.asList("Passed"));
        executionDetail.put("Reg26", Arrays.asList("Passed"));executionDetail.put("Reg27", Arrays.asList("Passed" ));executionDetail.put("Reg28", Arrays.asList("Passed"));executionDetail.put("Reg29", Arrays.asList("Passed"));executionDetail.put("Reg30", Arrays.asList("Passed"));
        executionDetail.put("Reg31", Arrays.asList("Passed"));executionDetail.put("Reg32", Arrays.asList("Passed" ));executionDetail.put("Reg33", Arrays.asList("Passed"));executionDetail.put("Reg34", Arrays.asList("Passed"));executionDetail.put("Reg35", Arrays.asList("Passed"));
        executionDetail.put("Reg36", Arrays.asList("Passed"));executionDetail.put("Reg37", Arrays.asList("Passed" ));executionDetail.put("Reg38", Arrays.asList("Passed"));executionDetail.put("Reg39", Arrays.asList("Passed"));executionDetail.put("Reg40", Arrays.asList("Passed"));
        executionDetail.put("Reg41", Arrays.asList("Passed"));executionDetail.put("Reg42", Arrays.asList("Passed" ));executionDetail.put("Reg43", Arrays.asList("Passed"));executionDetail.put("Reg44", Arrays.asList("Passed"));executionDetail.put("Reg45", Arrays.asList("Passed"));
        executionDetail.put("Reg46", Arrays.asList("Passed"));executionDetail.put("Reg47", Arrays.asList("Passed" ));executionDetail.put("Reg48", Arrays.asList("Passed"));executionDetail.put("Reg49", Arrays.asList("Passed"));executionDetail.put("Reg50", Arrays.asList("Passed"));
        executionDetail.put("Reg51", Arrays.asList("Passed"));executionDetail.put("Reg52", Arrays.asList("Passed" ));executionDetail.put("Reg53", Arrays.asList("Passed"));executionDetail.put("Reg54", Arrays.asList("Passed"));executionDetail.put("Reg55", Arrays.asList("Passed"));*/

        return executionDetail;
    }
}
