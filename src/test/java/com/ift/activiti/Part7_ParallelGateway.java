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
import java.util.List;

/**
 * 并行网关
 *
 * @author liufei
 * @date 2021/2/5 11:43
 */
@SpringBootTest
@ActiveProfiles("dev")
public class Part7_ParallelGateway {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @Test
    public void deploy() {
        String path = "bpmn/Part7_Parallel.bpmn";
        Deployment deployment = repositoryService.createDeployment().addClasspathResource(path)
                .name("Part7_Parallel_Gateway").deploy();
        System.out.println(deployment.getName());
    }
    @Test
    public void deleteDeployment() {
        repositoryService.deleteDeployment("5b26df76-6779-11eb-9e70-00e04c3625e7");
    }

    @Test
    public void startProcess() {
        runtimeService.startProcessInstanceByKey("myProcess_Parallel", "123");
    }

    @Test
    public void selectProcess() {
        List<String> assigneeIds = Arrays.asList("wukong", "tangseng");
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
        String taskId = "dc6675fd-677c-11eb-82ce-00e04c3625e7";
        taskService.addComment(taskId,
                "9997dec2-677a-11eb-8242-00e04c3625e7", "唐僧已经审核");
        taskService.complete(taskId);
    }


}
