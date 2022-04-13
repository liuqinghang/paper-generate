package com.work.university.controller;

import com.work.university.domain.user.User;
import com.work.university.service.UserService;
import com.work.university.tools.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@Controller
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
    public AjaxResult getQuestionType(@RequestParam("userId") String userId){
        User user = userService.getUserInfo(userId);
        return AjaxResult.success(user);
    }
    /**
     * register
     * code : 1 - 代表注册成功
     *      : 2 - 代表用户名重复
     *      : 3- 注册失败
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody User user){
        Integer registerCode = userService.register(user);
        if(registerCode == 1) {
            return AjaxResult.success();
        }else if(registerCode == 2){
            return AjaxResult.error("用户名重复,请检查用户名");
        }else{
            return AjaxResult.error("注册失败,请联系管理员");
        }
    }
    /**
     * login
     *
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody User user){
        boolean loginResult = userService.verifyUser(user);
        if(loginResult) {
            return AjaxResult.success(user);
        }else {
            return AjaxResult.error();
        }
    }
}

