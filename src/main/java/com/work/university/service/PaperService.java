package com.work.university.service;



import com.work.university.domain.RuleBean;
import com.work.university.domain.paper.Paper;
import com.work.university.domain.question.QuestionType;
import com.work.university.domain.util.TreeSelect;

import java.util.List;

/**
 * @author 路瞳
 */
public interface PaperService {
    /**
     * 获取试题类型
     * @return
     */
    public TreeSelect getChapterTree(String subject);
    /**
     *
     */
    public Paper getSmartPaper(RuleBean rule, QuestionService questionService);

}
