package com.ift.activiti;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 19870
 * @date 2021/1/27
 */
@SpringBootTest
public class Part4_Task {

    @Autowired
    private TaskService taskService;

    /**
     * 获取所有任务
     */
    @Test
    public void getTasks() {
        List<Task> tasks = taskService.createTaskQuery().list();
        for (Task task : tasks) {
            System.out.println("Id: " + task.getId());
            System.out.println("Name: " + task.getName());
            System.out.println("Assignee: " + task.getAssignee());
            System.out.println("TaskDefinitionKey: " + task.getTaskDefinitionKey());
        }
    }

    /**
     * 通过候选人获取任务
     */
    @Test
    public void getTaskByAssignee() {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("wukong").list();
        for (Task task : tasks) {
            System.out.println("Id: " + task.getId());
            System.out.println("Name: " + task.getName());
            System.out.println("Assignee: " + task.getAssignee());
            System.out.println("TaskDefinitionKey: " + task.getTaskDefinitionKey());
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void completeTask() {
        String taskId = "a3d41f11-60a5-11eb-b965-005056c00008";
        taskService.complete(taskId);
        System.out.println("完成任务");
    }

    /**
     * 拾取任务
     */
    @Test
    public void claimTask() {
        taskService.claim("bb0f52ae-617a-11eb-b1fa-005056c00008", "wukong");
    }

    /**
     * 归还任务
     */
    @Test
    public void unClaimTask() {
        taskService.unclaim("67ad0079-60aa-11eb-b1fa-005056c00008");
    }

    /**
     * 归还任务给指定的人
     */
    @Test
    public void claimTaskToOther() {
        taskService.setAssignee("67ad0079-60aa-11eb-b1fa-005056c00008", "bajie");
    }
}
