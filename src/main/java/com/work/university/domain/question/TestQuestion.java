package com.work.university.domain.question;

import lombok.Data;

import java.util.Date;
import java.util.List;

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
    public int subjectId;
    public String subject;
    public String chapterId;
    public String chapter;
    public double difficulty;
    public int master;
    public String masterName;
    public Date createTime;
    public String createUser;
    public Date updateTime;
    public String updateUser;
    public String permission;
    public double star;
    public double score;
    public List<Integer> pointIds;
    public String delFlag;
}
