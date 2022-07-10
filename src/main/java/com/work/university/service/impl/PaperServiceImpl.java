package com.work.university.service.impl;


import com.work.university.GenerateAlgorithm.test.Population;
import com.work.university.GenerateAlgorithm.test.GA;
import com.work.university.domain.RuleBean;
import com.work.university.domain.Selector;
import com.work.university.domain.paper.Paper;
import com.work.university.domain.question.TestQuestion;
import com.work.university.domain.util.TreeSelect;
import com.work.university.domain.util.TreeSelectEntity;
import com.work.university.mapper.PaperMapper;
import com.work.university.service.PaperService;
import com.work.university.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author 路瞳
 */
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private PaperMapper paperMapper;

    /**
     * 获取科目对应章节的下拉树列表
     *
     * @param subject
     * @return
     */
    @Override
    public TreeSelect getChapterTree(String subject) {
        List<TreeSelectEntity> res = paperMapper.getChapterTree(subject);
        List<Selector> subjects = questionService.getSubject();

        TreeSelect select = new TreeSelect();
        for(Iterator si = subjects.iterator();si.hasNext();){
            TreeSelect subselect = new TreeSelect();

        }
        return null;
    }

    /**
     * @param
     */
    @Override
    public Paper getSmartPaper(RuleBean rule, QuestionService questionService) {
        Paper resultPaper = null;
        rule.setTotalMark();
        // 迭代计数器
        int count = 0;
        int runCount = 100;
        // 适应度期望值
        double expand = 0.98;
        // 可自己初始化组卷规则rule
//        rule = new RuleBean();
//        rule.setTotalMark(20);
//        rule.setDifficulty(4.5);
//        rule.setSingleNum(2);
//        rule.setSingleScore(3);
//        rule.setMultiNum(1);
//        rule.setMultiScore(4);
//        rule.setSubjectiveNum(1);
//        rule.setSubjectiveScore(10);
//        rule.setPointIds("1#2#3#4#5#");
//        if (rule != null) {
            // 初始化种群
            Population population = new Population(20, true, rule,questionService);
            System.out.println("初次适应度  " + population.getFitness().getAdaptationDegree());
            while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
                count++;
                population = GA.evolvePopulation(population, rule, questionService);
                System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree());
            }
            System.out.println("进化次数： " + count);
            System.out.println(population.getFitness().getAdaptationDegree());
            resultPaper = population.getFitness();
//        }
        System.out.println(resultPaper);


        return resultPaper;
    }


    /**
     * 保存组卷结果
     *
     * @param paper
     */
    @Override
    public void savePaper(Paper paper) {
        paper.setCreateTime(new Date());
        paper.setCreateUser("1");
        paper.setPermission("0");
        paperMapper.savePaper(paper);
    }

    /**
     * 获取所有满足条件的Paper
     *
     * @param paper - paperId
     *              - paperName
     *              - createUser
     * @return
     */
    @Override
    public List<Paper> getPaper(Paper paper) {
        List<Paper> res = paperMapper.getPaper(paper);

        for(Paper p : res){
            ArrayList<TestQuestion> questionList = (ArrayList<TestQuestion>) questionService.getQuestionByIds(p.getPaperContent());
            p.setQuestionList(questionList);
        }
        return res;
    }


}
