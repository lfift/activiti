package com.ift.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 19870
 * @date 2021/1/27
 */
@SpringBootTest
public class Part3_ProcessInstance {

    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void startProcessInstance() {
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("myProcess_uelv3", "123");
        System.out.println("InstanceId: " + processInstance.getProcessInstanceId());
    }

    @Test
    public void suspendProcessInstance() {
        runtimeService.suspendProcessInstanceById("80381e6b-609f-11eb-8467-005056c00008");
        System.out.println("流程已挂起");
    }

    @Test
    public void activeProcessInstance() {
        runtimeService.activateProcessInstanceById("80381e6b-609f-11eb-8467-005056c00008");
        System.out.println("激活流程成功");
    }

    @Test
    public void deleteProcessInstance() {
        runtimeService.deleteProcessInstance("80381e6b-609f-11eb-8467-005056c00008", "不想做了");
        System.out.println("删除成功");
    }
}
