package com.work.university.domain.paper;


import com.work.university.domain.RuleBean;
import com.work.university.domain.question.TestQuestion;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 遗传算法中的个体，即一套可能的试卷。对试卷进行编码，而不是对整个题库编码
 *
 * @author 路瞳
 * @date
 * @version: 1.0
 */
@Data
public class Paper {
    /**
     * 个体id
     */
    private Integer id;
    /**
     * 适应度
     */
    private double adaptationDegree = 0.00;
    /**
     * 知识点覆盖率
     */
    private double kPCoverage = 0.00;
    /**
     * 试卷总分
     */
    private double totalScore = 0.00;
    /**
     * 试卷难度系数
     */
    private double difficulty = 0.00;
    /**
     * 个体包含的试题集合
     */
    private List<TestQuestion> questionList = new ArrayList<TestQuestion>();

    /**
     * paper_id -- db
     */
    private Integer paperId;
    /**
     * paper_name
     */
    private String paperName;
    /**
     * paper_content -- idString
     */
    private String paperContent;
    private String paperInfo;
    private String createUser;
    private Date createTime;
    private String permission;
    private String delFlag;

    public Paper(int size) {
        for (int i = 0; i < size; i++) {
            questionList.add(null);
        }
    }

    public Paper() {
        super();
    }

    public void setTotalScore(double score){
        this.totalScore = score;
    }
    /**
     * 计算试卷总分
     *
     * @return
     */
    public double getTotalScore() {
        if (totalScore == 0) {
            double total = 0;
            for (TestQuestion question : questionList) {
                total += question.getScore();
            }
            totalScore = total;
        }
        return totalScore;
    }
    /**
     * 保存试卷对应所有试题的id
     */
    public void setContent(String idString){
        this.paperContent = idString;
    }
    /**
     * 计算试卷对应所有试题的id
     */
    public String getContent(){
        Integer id = this.questionList.get(0).getQuestionId();
        String idString = String.valueOf(id);
        for (int i = 1; i < this.questionList.size(); i++) {
            idString += "," + this.questionList.get(i).getQuestionId();
        }
        return idString;
    }

    /**
     * 计算试卷难度
     * @param difficulty
     */
    public void setDifficulty(double difficulty){
        this.difficulty = difficulty;
    }
    /**
     * 计算试卷个体难度系数 计算公式： 每题难度*分数求和除总分
     *
     * @return
     */
    public double getDifficulty() {
        if (difficulty == 0) {
            double _difficulty = 0;
            for (TestQuestion question : questionList) {
                _difficulty += question.getScore() * question.getDifficulty();
            }
            difficulty = _difficulty / getTotalScore();
        }
        return difficulty;
    }

    /**
     * 获取试题数量
     *
     * @return
     */
    public int getQuestionSize() {
        return questionList.size();
    }

    /**
     * 计算知识点覆盖率 公式为：个体包含的知识点/期望包含的知识点
     *
     * @param rule
     */
    public void setKpCoverage(RuleBean rule) {
        if (kPCoverage == 0) {
            Set<String> result = new HashSet<String>();
            result.addAll(rule.getPointIds());
            Set<String> another = questionList.stream().map(question -> String.valueOf(question.getPointIds())).collect(Collectors.toSet());
            // 交集操作
            result.retainAll(another);
            kPCoverage = result.size() / rule.getPointIds().size();
        }
    }

    /**
     * 计算个体适应度 公式为：f=1-(1-M/N)*f1-|EP-P|*f2
     * 其中M/N为知识点覆盖率，EP为期望难度系数，P为种群个体难度系数，f1为知识点分布的权重
     * ，f2为难度系数所占权重。当f1=0时退化为只限制试题难度系数，当f2=0时退化为只限制知识点分布
     *
     * @param rule 组卷规则
     * @param f1   知识点分布的权重
     * @param f2   难度系数的权重
     */
    public void setAdaptationDegree(RuleBean rule, double f1, double f2) {
        if (adaptationDegree == 0) {
            adaptationDegree = 1 - (1 - getkPCoverage()) * f1 - Math.abs(rule.getDifficulty() - getDifficulty()) * f2;
        }
    }

    public boolean containsQuestion(TestQuestion question) {
        if (question == null) {
            for (int i = 0; i < questionList.size(); i++) {
                if (questionList.get(i) == null) {
                    return true;
                }
            }
        } else {
            for (TestQuestion aQuestionList : questionList) {
                if (aQuestionList != null) {
                    if (aQuestionList.equals(question)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 增加问题
     *
     * @param question
     */
    public void saveQuestion(int index, TestQuestion question) {
        this.questionList.set(index, question);
        this.totalScore = 0;
        this.adaptationDegree = 0;
        this.difficulty = 0;
        this.kPCoverage = 0;
    }

    public void addQuestion(TestQuestion question) {
        this.questionList.add(question);
        this.totalScore = 0;
        this.adaptationDegree = 0;
        this.difficulty = 0;
        this.kPCoverage = 0;
    }

    public TestQuestion getQuestion(int index) {
        return questionList.get(index);
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getkPCoverage() {
        return kPCoverage;
    }

    public double getAdaptationDegree() {
        return adaptationDegree;
    }

    public List<TestQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<TestQuestion> questionList) {
        this.questionList = questionList;
    }



    public void setPaperId(Integer id) {
        this.paperId = id;
    }

    public String getUserId() {
        return this.createUser;
    }

    public void setUserId(Integer id) {
        this.createUser = String.valueOf(id);
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }
}
