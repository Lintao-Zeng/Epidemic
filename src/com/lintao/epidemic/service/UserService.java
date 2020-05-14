package com.lintao.epidemic.service;

import com.lintao.epidemic.bean.UserInfo;

/**
 * service层
 */
public interface UserService {
    /**
     * 根据用户名和密码进行用户登录
     * @param user
     * @return
     */
    UserInfo findByAccount(UserInfo user);
}
