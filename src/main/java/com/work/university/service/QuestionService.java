package com.work.university.service;



import com.work.university.domain.Selector;
import com.work.university.domain.TestQuestion;

import java.util.List;
import java.util.Map;

/**
 * @author 路瞳
 */
public interface QuestionService {
    /**
     * 获取试题类型
     * @return
     */
    public List<Selector> getQuestionType();
    /**
     * 获取科目类型
     */
    public List<Selector> getSubject();
    /**
     * 获取科目对应的章节
     */
    public List<Selector> getChapter(String subjectId);

    /**
     * 获取知识点
     */
    public Map<String,String> getKnowledge(String subjectId, String chapterId);
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
