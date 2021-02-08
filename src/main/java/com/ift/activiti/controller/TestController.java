package com.ift.activiti.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 19870
 * @date 2021/1/26
 */
@RestController
public class TestController {

    @PreAuthorize("hasRole('ROLE_HELLO')")
    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
