package com.work.university.service.impl;

import com.work.university.domain.user.User;
import com.work.university.mapper.UserMapper;
import com.work.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public User getUserInfo(String userId) {
        if(userId == ""){
            return null;
        }
        return userMapper.getUserInfo(Integer.valueOf(userId));

    }

    /**
     * 新用户注册
     *
     * @param user
     * @return code : 1 - 代表注册成功
     *      : 2 - 代表用户名重复
     *      : 3- 注册失败
     */
    @Override
    public Integer register(User user) {
        Integer res = userMapper.verifyUserExit(user.getUsername());
        if(res >= 1){
            return 2;
        }else {
            user.setCreateTime(new Date());
            user.setUserType("1");
            user.setDelFlag("0");
            userMapper.register(user);
        }
        return 1;
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public Boolean verifyUser(User user) {
        Integer res = userMapper.verifyUser(user);
        if(res > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 用户信息修改
     *
     * @param user
     * @return 1 代表更新成功
     */
    @Override
    public Integer updateUserInfo(User user) {
        return null;
    }
}
