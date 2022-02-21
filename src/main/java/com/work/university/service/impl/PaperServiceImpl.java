package com.work.university.service.impl;


import com.work.university.domain.QuestionType;
import com.work.university.mapper.QuestionMapper;
import com.work.university.service.PaperService;
import com.work.university.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 路瞳
 */
@Service
public class PaperServiceImpl implements PaperService {
    /**
     * 获取试题类型
     *
     * @return
     */
    @Override
    public List<QuestionType> getType() {
        return null;
    }


}
