package com.work.university.controller;

import com.work.university.domain.QuestionType;
import com.work.university.domain.Selector;
import com.work.university.domain.TestQuestion;
import com.work.university.service.QuestionService;
import com.work.university.tools.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 路瞳
 */
@RequestMapping("/paper")
@Controller
@ResponseBody
public class PaperController {

    @Autowired
    private QuestionService questionService;

    /**
     * 获取试题类型
     * @return
     */
    @GetMapping("/getQuestionType")
    public AjaxResult getType(){
        List<Selector> res = questionService.getQuestionType();
        if(res!=null && res.size() > 0) {
            return AjaxResult.success(res);
        } else {
            return AjaxResult.error("无");
        }
    }

    /**
     * 保存试题信息
     *
     *
     */
    @GetMapping("/savePaper")
    public AjaxResult saveQuestion(@RequestBody TestQuestion question){
        return AjaxResult.success();
    }


}
