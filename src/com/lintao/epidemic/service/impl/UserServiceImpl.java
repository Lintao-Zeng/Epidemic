package com.lintao.epidemic.service.impl;

import com.lintao.epidemic.bean.UserInfo;
import com.lintao.epidemic.service.UserService;
import com.lintao.epidemic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名和密码进行用户登录
     * @param user
     * @return
     */
    @Override
    public UserInfo findByAccount(UserInfo user) {
        return userMapper.findByAccount(user);
    }
}
