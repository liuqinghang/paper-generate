package com.work.university.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.work.university.domain.Selector;
import com.work.university.domain.question.SingleChoose;
import com.work.university.domain.question.TestQuestion;
import com.work.university.mapper.QuestionMapper;
import com.work.university.tools.utils.RedisUtil;
import com.work.university.tools.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.work.university.service.QuestionService;

import java.util.*;


/**
 * @author 路瞳
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    private final Integer SINGLE_CHOOSE = 1;
    private final Integer MULTI_CHOOSE = 2;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private RedisUtil redisUtil;


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
        String chooseStr = null;
        System.out.println(question.getContent());
        if (question.getContent().contains("\\$")) {
            String[] tar = question.getContent().split("\\$");
            question.setContent(tar[0].substring(0,tar[0].length()-1));
            chooseStr = tar[1];
        }

        questionMapper.saveQuestion(question);
        questionMapper.saveQuestionKnowledge(question);

        if (StringUtils.isNotNull(chooseStr)) {
            List<SingleChoose> res = new ArrayList<SingleChoose>();
            if (SINGLE_CHOOSE == question.getType() || MULTI_CHOOSE == question.getType()) {
                JSONArray jsonArray = JSONArray.parseArray(chooseStr);
                for (int i = 0; i < jsonArray.size(); i++) {
                    SingleChoose choose = new SingleChoose();
                    choose.setQuestionId(question.getQuestionId());
                    choose.setChoosedContent(jsonArray.getString(i));
                    res.add(choose);
                }
            } else {
                // 非选择题
                SingleChoose choose = new SingleChoose();
                choose.setQuestionId(question.getQuestionId());
                choose.setChoosedContent(chooseStr);
                res.add(choose);
            }
            questionMapper.saveQuestionSingleChoose(res);
        }

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
     * 获取单选题对应的所有选项
     *
     * @param questionId
     */
    @Override
    public List<SingleChoose> getQuestionSingleChoose(String questionId) {
        return questionMapper.getQuestionSingleChoose(questionId);
    }

    /**
     * 根据 rule的设定 获取对应的试题集
     *
     * @param type     试题类型
     * @param idString 章节列表
     */
    @Override
    public TestQuestion[] getQuestionArray(int type, String idString) {
        TestQuestion question = new TestQuestion();
        question.setType(type);
        question.setChapterId(idString.substring(1, idString.length() - 1));
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


    /**
     * 测试redis浏览量 缓存功能
     * 命名
     */
    private static String VIEW_KEY(Integer questionId){
        return "QV:" + questionId;
    }

    //从redis里取出点赞数
    private Integer getLikesFromRedis(Integer questionId){
        Integer likes;
        try{
            //从redis中取出键为LIKE_KEY(Integer id)的值
            likes = (Integer)redisUtil.get(VIEW_KEY(questionId));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return likes;
    }

    @Override
    public int getViews(Integer id){
        Integer views = getLikesFromRedis(id);
        //如果redis里能取出一个非null值（说明redis有维护这个帖子的点赞数），就直接返回
        if(views != null)return views;

        //redis中没存点赞数的情况
        //先从mysql里把点赞数取出来
        views = Optional.ofNullable(questionMapper.getLikesByPrimaryKey(id)).orElse(0);

        //存到redis里
        redisUtil.set(VIEW_KEY(id),views);
        return views;
    }

    @Override
    public void views(Integer id){
        Integer views = getLikesFromRedis(id);
        //如果redis里能取出一个非null值（说明redis有维护这个帖子的点赞数），就直接在原基础上+1
        if(views != null){
            redisUtil.set(VIEW_KEY(id),views + 1);
        }else{
            //redis取不出，就从mysql取
            views = Optional.ofNullable(questionMapper.getLikesByPrimaryKey(id)).orElse(0);
            //存到redis里
            redisUtil.set(VIEW_KEY(id),views + 1);
        }
    }


}
