package com.ift.activiti.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录失败处理器
 *
 * @author liufei
 * @date 2021/2/8 10:05
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Map<String, Object> results = new HashMap<>(2);
        results.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        if (exception instanceof LockedException) {
            results.put("message", "账户被锁定，请联系管理员！");
        } else if (exception instanceof CredentialsExpiredException) {
            results.put("message", "密码过期，请联系管理员！");
        } else if (exception instanceof AccountExpiredException) {
            results.put("message", "账户过期，请联系管理员！");
        } else if (exception instanceof DisabledException) {
            results.put("message", "账户被禁用，请联系管理员！");
        } else if (exception instanceof BadCredentialsException) {
            results.put("message", "用户名或密码错误，请重新登录！");
        }
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(results));
        writer.flush();
        writer.close();
    }
}
