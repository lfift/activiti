package com.ift.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 19870
 * @date 2021/1/27
 */
@SpringBootTest
public class Cap2_ProcessDefinitionTest {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void getProcessDefinitions() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            System.out.println("Name: " + processDefinition.getName());
            System.out.println("Key: " + processDefinition.getKey());
            System.out.println("Id: " + processDefinition.getId());
            System.out.println("DeploymentId: " + processDefinition.getDeploymentId());
            System.out.println("Version: " + processDefinition.getVersion());
        }
    }

    @Test
    public void deleteDeployment() {
        String id = "2cfa65b3-5fec-11eb-ac33-005056c00008";
        repositoryService.deleteDeployment(id, true);
        System.out.println("删除成功！");
    }
}
