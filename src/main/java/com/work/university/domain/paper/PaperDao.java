package com.work.university.domain.paper;

import lombok.Data;

import java.util.Date;

/**
 * @author 路瞳
 * @describe paper 数据库实体类
 */
@Data
public class PaperDao {
    public Integer paperId;
    public String paperName;
    public Double totalScore;
    public String paperContent;
    public Double difficulty;
    public String paperInfo;
    public String createUser;
    public Date createTime;
    public String updateUser;
    public Date updateTime;
    public String permission;
    public String delFlag;


    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }
}
