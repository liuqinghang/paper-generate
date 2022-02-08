package com.work.university.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 路瞳
 */
@Data
public class TestQuestion {
    public int questionId;
    public String content;
    public String answer;
    public int count;
    public int type;
    public String typeName;
    public String subject;
    public float difficulty;
    public int master;
    public String masterName;
    public Date createTime;
    public String createUser;
    public Date updateTime;
    public String updateUser;
    public String permission;
    public float star;
    public String delFlag;
}
