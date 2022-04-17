package com.work.university.controller;

import com.sun.istack.internal.NotNull;
import com.work.university.domain.Selector;
import com.work.university.domain.question.SingleChoose;
import com.work.university.domain.question.TestQuestion;
import com.work.university.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.work.university.tools.domain.AjaxResult;

import java.util.List;
import java.util.Map;

/**
 * @author 路瞳
 */
@RequestMapping("/question")
@Controller
@ResponseBody
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取试题类型
     * @return
     */
    @GetMapping("/getQuestionType")
    public AjaxResult getQuestionType(){
        List<Selector> res = questionService.getQuestionType();
        if(res!=null && res.size() > 0) {
            return AjaxResult.success(res);
        } else {
            return AjaxResult.error("无");
        }
    }
    /**
     * 获取科目类型
     */
    @GetMapping("/getSubject")
    public AjaxResult getSubject(){
        List<Selector> res = questionService.getSubject();
        if(res!=null && res.size() > 0) {
            return AjaxResult.success(res);
        } else {
            return AjaxResult.error("无");
        }
    }

    /**
     * 获取科目对应章节
     */
    @GetMapping("/getChapter")
    public AjaxResult getChapter(@RequestParam("subjectId") String subjectId) {
        List<Selector> res = questionService.getChapter(subjectId);
        if(res!=null && res.size() > 0) {
            return AjaxResult.success(res);
        } else {
            return AjaxResult.error("无");
        }
    }
    /**
     * 获取对应的知识点
     */
    @GetMapping("/getKnowledge")
    public AjaxResult getKnowledge(@RequestParam(value="subjectId", required = false) String subjectId,
                                   @RequestParam(value="chapterId",required = false) String chapterId) {
        Map<String,String> res= questionService.getKnowledge(subjectId,chapterId);
        if(res!=null && res.size() > 0) {
            return AjaxResult.success(res);
        } else {
            return AjaxResult.error("无");
        }
    }

    /**
     * 获取所有试题
     *
     */
    @PostMapping("/getQuestion")
    public AjaxResult getQuestion(@RequestBody TestQuestion question){
        List<TestQuestion> res = questionService.getQuestion(question);
        for(TestQuestion t : res){
            int ts = t.getQuestionId();
            List<SingleChoose> tar = questionService.getQuestionSingleChoose(String.valueOf(ts));
            t.setDetail(tar);
        }
        return AjaxResult.success(res);
    }

    /**
     * 获取试题对应的详情,比如单选题对应的选项
     * 只用于获取单选题的所有选项  -  v1.0
     */
    @GetMapping("/getDetail")
    public AjaxResult getDetail(@RequestParam(value="questionId", required = true) String questionId){
        List<SingleChoose> res = questionService.getQuestionSingleChoose(questionId);
        return AjaxResult.success(res);
    }

    /**
     * 保存试题信息
     *
     */
    @PostMapping("/saveQuestion")
    public AjaxResult saveQuestion(@RequestBody TestQuestion question){
        questionService.saveQuestion(question);
        return AjaxResult.success();
    }

    /**
     * test redis cache
     */
    @GetMapping("/{id}")
    public AjaxResult getLikes(@NotNull @PathVariable Integer id){
        return AjaxResult.success(questionService.getViews(id));
    }

    @PostMapping("/{id}")
    public AjaxResult likes(@NotNull @PathVariable Integer id){
        questionService.views(id);
        return AjaxResult.success(questionService.getViews(id));
    }

}
