package com.lintao.epidemic.mapper;

import com.lintao.epidemic.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * dao层，对数据库进行增删改查
 */
@Mapper
public interface UserMapper {
    /**
     * 用户登录
     * @param user
     * @return
     */
    @Select(value="SELECT u.user_id,u.account,u.password,u.user_name FROM users u " +
            "WHERE u.account=#{account} AND u.password =#{password} AND u.del_flag = 0")
    UserInfo findByAccount(UserInfo user);
}
