package com.ift.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 19870
 * @date 2021/1/28
 */
@SpringBootTest
public class Part5_HistoricTaskInstance {

    @Autowired
    private HistoryService historyService;

    @Test
    public void selectTaskInstanceHistoryByUser() {
        List<HistoricTaskInstance> taskInstances =
                historyService.createHistoricTaskInstanceQuery()
                        .taskAssignee("bajie").orderByHistoricTaskInstanceEndTime().asc().list();
        for (HistoricTaskInstance taskInstance : taskInstances) {
            System.out.println("Id: " + taskInstance.getId());
            System.out.println("Name: " + taskInstance.getName());
            System.out.println("InstanceId: " + taskInstance.getProcessInstanceId());
        }
    }

    @Test
    public void selectTaskHistoryByInstanceId() {
        List<HistoricTaskInstance> taskInstances =
                historyService.createHistoricTaskInstanceQuery()
                        .processInstanceId("a3d0c3ad-60a5-11eb-b965-005056c00008")
                        .orderByHistoricTaskInstanceEndTime().asc().list();
        for (HistoricTaskInstance taskInstance : taskInstances) {
            System.out.println("Id: " + taskInstance.getId());
            System.out.println("Name: " + taskInstance.getName());
            System.out.println("Assignee: " + taskInstance.getAssignee());
        }
    }
}
