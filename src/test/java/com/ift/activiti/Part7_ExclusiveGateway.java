package com.ift.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 排他网关
 * 
 * @author liufei
 * @date 2021/2/5 11:43
 */
@SpringBootTest
@ActiveProfiles("dev")
public class Part7_ExclusiveGateway {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Test
    public void deploy() {
        String path = "bpmn/Part7_Exclusive.bpmn";
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(path)
                .name("Part7_Exclusive_Gateway").deploy();
        System.out.println(deployment.getName());
    }
    @Test
    public void deleteDeployment() {
        repositoryService.deleteDeployment("5b26df76-6779-11eb-9e70-00e04c3625e7");
    }

    @Test
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("myProcess_Exclusive", "111");
    }

    @Test
    public void selectProcess() {
        List<String> assigneeIds = Arrays.asList("bajie","wukong", "tangseng");
        List<Task> tasks = taskService.createTaskQuery().taskAssigneeIds(assigneeIds).list();
        for (Task task : tasks) {
            System.out.println("Id: " + task.getId());
            System.out.println("Name: " + task.getName());
            System.out.println("Assignee: " + task.getAssignee());
            System.out.println("InstanceId: " + task.getProcessInstanceId());
        }
    }

    @Test
    public void completeTask() {
        String taskId = "208c1487-6783-11eb-b6c6-00e04c3625e7";
        String processInstanceId = "b910d7ac-6782-11eb-ba5c-00e04c3625e7";
        String comment = "悟空审批通过";
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("day", "100");
        taskService.addComment(taskId, processInstanceId, comment);
//        taskService.complete(taskId, variables);
        taskService.complete(taskId);
    }


}
