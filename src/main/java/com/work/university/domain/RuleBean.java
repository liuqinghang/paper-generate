package com.work.university.domain;

import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 组卷规则Bean
 *
 * @author: xlli
 * @CreateDate: 2016-4-28 下午05:02:05
 * @version: 1.0
 */
@Data
public class RuleBean {
    /**
     * 规则id
     */
    private long id;
    /**
     * 规则对应的试卷id
     */
    private long formId;
    /**
     * 试卷总分
     */
    private int totalMark;
    /**
     * 试卷期望难度系数
     */
    private double difficulty;
    /**
     * 单选题数量
     */
    private int singleNum;
    /**
     * 单选题单个分值
     */
    private double singleScore;
    /**
     * 多选题数量
     */
    private int multiNum;
    /**
     * 多选题单个分值
     */
    private double multiScore;
    /**
     * 填空题数量
     */
    private int completeNum;
    /**
     * 填空题单个分值
     */
    private double completeScore;
    /**
     * 主观题数量
     */
    private int subjectiveNum;
    /**
     * 主观题单个分值
     */
    private double subjectiveScore;
    /**
     * 试卷包含的知识点id -- deprecate
     * 对应章节id
     */
    private List<String> pointIds;


    public void setPointIds(String pointIds) {
        // 是否是表单传过来的数据
        if (pointIds.endsWith("#")) {
            pointIds = pointIds.substring(0, pointIds.lastIndexOf('#'));
        }
        // 使用HashSet去重
        this.pointIds = new ArrayList<>(new HashSet<>(Arrays.asList(pointIds.split("#"))));
    }

    public void setTotalMark(){
        this.totalMark = 0;
        this.totalMark+= this.singleNum*this.singleScore;
        this.totalMark+= this.multiNum*this.multiScore;
        this.totalMark+= this.completeNum*this.completeScore;
        this.totalMark+= this.subjectiveNum*this.subjectiveScore;
    }

}
