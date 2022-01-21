package com.work.university.service.impl;


import com.work.university.domain.QuestionType;
import com.work.university.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.work.university.service.QuestionService;
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
     * @return
     */
    @Override
    public List<QuestionType> getType() {
        return questionMapper.getAllType();
    }
}
