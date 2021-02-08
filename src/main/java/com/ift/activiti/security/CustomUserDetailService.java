package com.ift.activiti.security;

import com.ift.activiti.entity.UserInfo;
import com.ift.activiti.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 用户信息
 *
 * @author liufei
 * @date 2021/2/8 9:11
 */
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("账号或密码错误！");
        }
        return userInfo;
    }
}
