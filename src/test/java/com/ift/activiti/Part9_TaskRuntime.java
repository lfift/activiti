package com.ift.activiti;

import com.ift.activiti.security.SecurityUtil;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

/**
 * @author liufei
 * @date 2021/2/7 16:08
 */
@SpringBootTest
@ActiveProfiles("dev")
public class Part9_TaskRuntime {

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private TaskRuntime taskRuntime;

    /**
     * 查询任务列表
     */
    @Test
    public void listTask() {
        securityUtil.logInAs("bajie");
        Page<Task> page = taskRuntime.tasks(Pageable.of(0, 10));
        System.out.println("任务数量： " + page.getTotalItems());
        List<Task> content = page.getContent();
        for (Task task : content) {
            System.out.println("Id: " + task.getId());
            System.out.println("Assignee: " + task.getAssignee());
            System.out.println("Name: " + task.getName());
            System.out.println("BusinessKey: " + task.getBusinessKey());
            System.out.println("ProcessInstanceId: " + task.getProcessInstanceId());
            System.out.println(task);
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void complete() {
        securityUtil.logInAs("bajie");
        String taskId = "e8122d2c-6797-11eb-b112-00e04c3625e7";
        Task task = taskRuntime.task(taskId);
        if (task.getAssignee() == null) {
            taskRuntime.claim(TaskPayloadBuilder.claim()
                    .withTaskId(taskId).withAssignee("bajie").build());
        }
        taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).build());
        System.out.println("任务完成！");
    }

}
