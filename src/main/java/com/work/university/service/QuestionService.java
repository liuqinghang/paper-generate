package com.work.university.service;



import com.work.university.domain.QuestionType;
import com.work.university.domain.TestQuestion;

import java.util.List;

/**
 * @author 路瞳
 */
public interface QuestionService {
    /**
     * 获取试题类型
     * @return
     */
    public List<QuestionType> getType();

    /**
     * 保存试题
     *
     */
    public void saveQuestion(TestQuestion question);

    /**
     * 获取所有试题
     * @return
     */
    public List getQuestion();
}
