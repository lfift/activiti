package com.ift.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Deployment测试
 * @author 19870
 * @date 2021/1/26
 */
@SpringBootTest
public class Cap1_DeploymentTest {

    @Autowired
    private RepositoryService repositoryService;


    @Test
    public void initDeploymentBPMN() {
        String filePath = "bpmn/Part4_Task_claim.bpmn";
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(filePath).name("请假流程审批测试拾取_Part4").deploy();
        System.out.println(deployment.getName());
    }

    @Test
    public void initDeploymentZIP() {
        String filePath = "bpmn/QingJia_Deployment.zip";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
        ZipInputStream zis = new ZipInputStream(is);
        repositoryService.createDeployment().addZipInputStream(zis).name("请假流程审批_V3").deploy();
    }

    @Test
    public void getDeployment() {
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        for (Deployment deployment : list) {
            System.out.println("ID: " + deployment.getId());
            System.out.println("Name: " + deployment.getName());
            System.out.println("Key: " + deployment.getKey());
            System.out.println("Version: " +deployment.getVersion());
        }
    }
}
