package com.work.university.service;



import com.work.university.domain.QuestionType;

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
}
