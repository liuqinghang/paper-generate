package com.work.university.service.impl;


import com.work.university.domain.Selector;
import com.work.university.domain.question.TestQuestion;
import com.work.university.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.work.university.service.QuestionService;

import java.util.Date;
import java.util.List;
import java.util.Map;


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
    public List<Selector> getQuestionType() {
        return questionMapper.getAllType();
    }

    /**
     * 获取科目类型
     */
    @Override
    public List<Selector> getSubject() {
        return questionMapper.getSubject();
    }

    /**
     * 获取科目对应的章节
     *
     * @param subjectId
     */
    @Override
    public List<Selector> getChapter(String subjectId) {
        return questionMapper.getChapter(subjectId);
    }

    /**
     * 获取知识点
     *
     * @param subjectId
     */
    @Override
    public Map<String, String> getKnowledge(String subjectId, String chapterId) {
//        return questionMapper.getKnowledge(subjectId,chapterId);
        return null;
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
        questionMapper.saveQuestionKnowledge(question);

    }

    /**
     * 获取所有试题
     *
     * @return
     */
    @Override
    public List getQuestion(TestQuestion question) {
        return questionMapper.getQuestion(question);
    }

    /**
     * 根据 rule的设定 获取对应的试题集
     *
     * @param type     试题类型
     * @param idString 章节列表
     */
    @Override
    public TestQuestion[] getQuestionArray(int type, String idString){
        TestQuestion question = new TestQuestion();
        question.setType(type);
        question.setChapterId(idString.substring(1,idString.length()-1));
        return questionMapper.getQuestionThroughPaperGenerate(question);
    }

    /**
     * 根据 rule的设定 获取对应的试题集
     *
     * @param question 排除当前节点
     */
    @Override
    public TestQuestion[] getQuestionListWithOutSId(TestQuestion question) {
        return questionMapper.getQuestionThroughPaperGenerate(question);
    }


}
