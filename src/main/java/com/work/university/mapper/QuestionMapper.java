package com.work.university.mapper;

import com.work.university.domain.Selector;
import com.work.university.domain.question.SingleChoose;
import com.work.university.domain.question.TestQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * 保存单选题对应选项
     */
    public void saveQuestionSingleChoose(List<SingleChoose> list);
    /**
     * 获取单选题对应的所有选项
     */
    public List<SingleChoose> getQuestionSingleChoose(String questionId);
    /**
     * 获取所有试题
     */
    public List<TestQuestion> getQuestion(TestQuestion question);

    public TestQuestion[] getQuestionThroughPaperGenerate(TestQuestion question);

    /**
     * 测试redis 浏览量缓存
     * @param questionId
     * @return
     */
    @Select("SELECT page_view FROM question_view WHERE question_id=#{questionId}")
    Integer getLikesByPrimaryKey(Integer questionId);

    @Update("UPDATE question_view SET page_view=#{pageView} WHERE question_id=#{questionId}")
    void setLikesByPrimaryKey(@Param("pageView")Integer pageView, @Param("questionId")Integer questionId);

}
