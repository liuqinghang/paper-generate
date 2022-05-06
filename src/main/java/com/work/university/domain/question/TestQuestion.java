package com.work.university.domain.question;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author 路瞳
 */
@Data
public class TestQuestion {
    private int questionId;
    private String content;
    private String answer;
    private int count;
    private List<SingleChoose> detail;
    private int type;
    private String typeName;
    private int subjectId;
    private String subject;
    private String chapterId;
    private String chapter;
    private double difficulty;
    private int master;
    private String masterName;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    private String permission;
    private double star;
    private double score;
    private List<Integer> pointIds;
    private String delFlag;
}
