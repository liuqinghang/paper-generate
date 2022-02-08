package com.work.university.controller;

import com.work.university.domain.QuestionType;
import com.work.university.domain.TestQuestion;
import com.work.university.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.work.university.tools.domain.AjaxResult;

import java.util.List;

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
    @GetMapping("/getType")
    public AjaxResult getType(){
        List<QuestionType> res = questionService.getType();
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
    @GetMapping("getQuestion")
    public AjaxResult getQuestion(){

        List<TestQuestion> res = questionService.getQuestion();

        return AjaxResult.success(res);
    }

    /**
     * 保存试题信息
     *
     *
     */
    @PostMapping("/saveQuestion")
    public AjaxResult saveQuestion(@RequestBody TestQuestion question){
        questionService.saveQuestion(question);
        return AjaxResult.success();
    }
}
