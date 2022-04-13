package com.work.university.domain.user;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    public String userId;
    public String name;
    public String username;
    public String password;
    public String sex;
    public String department;
    public Date createTime;
    public Date updateTime;
    public String userType;
    public String delFlag;
}
