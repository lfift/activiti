package com.ift.activiti;

import com.ift.activiti.security.SecurityUtil;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 流程实例新API
 *
 * @author liufei
 * @date 2021/2/5 17:28
 */
@SpringBootTest
public class Part8_ProcessRuntime {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    /**
     * 查询流程实例
     */
    @Test
    public void listProcessInstance() {
        securityUtil.logInAs("bajie");
        Page<ProcessInstance> page = processRuntime.processInstances(Pageable.of(0, 100));
        System.out.println("流程实例总数：" + page.getTotalItems());
        List<ProcessInstance> content = page.getContent();
        for (ProcessInstance processInstance : content) {
            System.out.println("Id: " + processInstance.getId());
            System.out.println("BusinessKey: " + processInstance.getBusinessKey());
            System.out.println("name: " + processInstance.getName());
            System.out.println("status: " + processInstance.getStatus());
            System.out.println("StartDate: " + processInstance.getStartDate());
            System.out.println("processDefinitionKey: " + processInstance.getProcessDefinitionKey());
        }
    }

    /**
     * 发起流程
     */
    @Test
    public void startProcess() {
        securityUtil.logInAs("bajie");
        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder.start()
                .withName("测试新API-并行网关")
                .withProcessDefinitionKey("myProcess_Parallel")
                .withBusinessKey("123").build());
        System.out.println("Id: " + processInstance.getId());
        System.out.println("BusinessKey: " + processInstance.getBusinessKey());
        System.out.println("name: " + processInstance.getName());
        System.out.println("status: " + processInstance.getStatus());
        System.out.println("StartDate: " + processInstance.getStartDate());
        System.out.println("processDefinitionKey: " + processInstance.getProcessDefinitionKey());
    }

    /**
     * 挂起流程
     */
    @Test
    public void suspendProcess() {
        String processInstanceId = "b60ca75c-6796-11eb-a7fa-00e04c3625e7";
        securityUtil.logInAs("bajie");
        processRuntime.suspend(ProcessPayloadBuilder.suspend(processInstanceId));
    }

    /**
     * 激活流程实例
     */
    @Test
    public void resumeProcessInstance() {
        String processInstanceId = "b60ca75c-6796-11eb-a7fa-00e04c3625e7";
        securityUtil.logInAs("bajie");
        processRuntime.resume(ProcessPayloadBuilder.resume(processInstanceId));
    }

    /**
     * 删除流程实例
     */
    @Test
    @Transactional
    public void deleteProcessInstance() {
        String processInstanceId = "e80a64f7-6797-11eb-b112-00e04c3625e7";
        securityUtil.logInAs("bajie");
        processRuntime.delete(ProcessPayloadBuilder.delete()
                .withProcessInstanceId(processInstanceId).withReason("123").build());
    }

    /**
     * 获取流程变量
     */
    @Test
    public void getProcessVariable() {
        String processInstanceId = "e80a64f7-6797-11eb-b112-00e04c3625e7";
        securityUtil.logInAs("bajie");
        List<VariableInstance> variables = processRuntime.variables(ProcessPayloadBuilder.variables().withProcessInstanceId(processInstanceId).build());
        for (VariableInstance variable : variables) {
            System.out.println("name: " + variable.getName());
            System.out.println("value: " + variable.getValue());
            System.out.println("type: " + variable.getType());
            System.out.println("processInstanceId: " + variable.getProcessInstanceId());
            System.out.println("taskId: " + variable.getTaskId());
        }
    }


}
