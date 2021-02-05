package com.ift.activiti;

import com.ift.activiti.security.SecurityUtil;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
