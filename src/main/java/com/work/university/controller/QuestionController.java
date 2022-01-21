package com.work.university.controller;

import com.work.university.domain.QuestionType;
import com.work.university.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @GetMapping("/getType")
    public AjaxResult getType(){
        List<QuestionType> res = questionService.getType();
        if(res!=null && res.size() > 0) {
            return AjaxResult.success(res);
        } else {
            return AjaxResult.error("无");
        }
    }
}
