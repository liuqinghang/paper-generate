package com.work.university.service;



import com.work.university.domain.user.User;


/**
 * @author 路瞳
 */
public interface UserService {
    /**
     * 获取用户信息
     * @return
     */
    public User getUserInfo(String userId);

    /**
     * 新用户注册
     * @param user
     * @return
     */
    Integer register(User user);
    /**
     * 用户登录
     * @param user
     * @return
     */
    Boolean verifyUser(User user);
    /**
     * 用户信息修改
     * @param user
     * @return 1 代表更新成功
     */
    Integer updateUserInfo(User user);
}
