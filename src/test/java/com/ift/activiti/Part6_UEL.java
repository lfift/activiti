package com.ift.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 19870
 * @date 2021/1/28
 */
@SpringBootTest
public class Part6_UEL {

    @Autowired
    public RuntimeService runtimeService;

    @Autowired
    public TaskService taskService;

    /**
     * 带变量初始化
     */
    @Test
    public void initProcessInstanceWithArgs() {
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("ZhiXingRen", "wukong");
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("myProcess_UEL_V1",
                        "aaa", variables);
        System.out.println("InstanceId: " + processInstance.getProcessInstanceId());
    }

    /**
     * 完成任务带参数
     */
    @Test
    public void completeTaskWithArgs() {
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("pay", 101);
        taskService.complete("5a9ea972-6174-11eb-96b8-005056c00008", variables);
    }

    /**
     * 启动流程实例带参数，使用实体类
     */
    @Test
    public void initProcessInstanceWithClassArgs() {
        UEL_POJO uelPojo = new UEL_POJO();
        uelPojo.setPay("101");
        uelPojo.setZhixingren("bajie");
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("uelpojo", uelPojo);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("myProcess_uelv3",
                        "aaa", variables);
        System.out.println("InstanceId: " + processInstance.getProcessInstanceId());
    }

    /**
     * 启动流程实例带参数，指定多个候选人
     */
    @Test
    public void initProcessInstanceWithCandiDateArgs() {
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("houxuanren", "bajie,wukong");
        taskService.complete("43f55cd7-617a-11eb-8503-005056c00008", variables);
    }

    /**
     * 直接指定流程变量
     */
    @Test
    public void otherArgs() {
        runtimeService.setVariable("", "", "");
    }

    /**
     * 局部变量
     */
    @Test
    public void otherLocalArgs() {
        taskService.setVariable("", "", "");
    }
}
