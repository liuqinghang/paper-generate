package com.work.university.service.impl;


import com.work.university.domain.QuestionType;
import com.work.university.domain.TestQuestion;
import com.work.university.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.work.university.service.QuestionService;

import java.util.Date;
import java.util.List;


/**
 * @author 路瞳
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 获取试题类型
     *
     * @return
     */
    @Override
    public List<QuestionType> getType() {
        return questionMapper.getAllType();
    }

    /**
     * 保存试题
     *
     * @param question
     */
    @Override
    public void saveQuestion(TestQuestion question) {
        question.setMaster(1);
        question.setCreateTime(new Date());
        question.setDelFlag("0");
        questionMapper.saveQuestion(question);
    }

    /**
     * 获取所有试题
     *
     * @return
     */
    @Override
    public List getQuestion() {
        return questionMapper.getQuestion();
    }


}
