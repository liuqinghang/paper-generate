package com.work.university.mapper;

import com.work.university.domain.Selector;
import com.work.university.domain.question.TestQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 路瞳
 */
@Mapper
public interface QuestionMapper {
    /**
     * 获取所有试题类型
     * @return
     */
    public List<Selector> getAllType();
    /**
     * 获取科目类型
     */
    public  List<Selector> getSubject();
    /**
     * 获取章节类型
     */
    public  List<Selector> getChapter(String subjectId);
    /**
     * 添加试题
     */
    public void saveQuestion(TestQuestion question);
    /**
     * 添加试题知识点相关信息
     */
    public void saveQuestionKnowledge(TestQuestion question);
    /**
     * 获取所有试题
     */
    public List<TestQuestion> getQuestion(TestQuestion question);

    public TestQuestion[] getQuestionThroughPaperGenerate(TestQuestion question);
}
