package com.ift.activiti.mapper;

import com.ift.activiti.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息Mapper
 *
 * @author liufei
 * @date 2021/2/8 9:21
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 根据账号查询用户信息
     *
     * @param username 账号
     * @return 用户信息
     */
    @Select("select id,name,address,username,password,roles from user where username = #{username}")
    UserInfo selectByUsername(@Param("username") String username);
}
