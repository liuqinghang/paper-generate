package com.work.university.controller;

import com.work.university.domain.RuleBean;
import com.work.university.domain.Selector;
import com.work.university.domain.paper.Paper;
import com.work.university.domain.question.SingleChoose;
import com.work.university.domain.question.TestQuestion;
import com.work.university.service.PaperService;
import com.work.university.service.QuestionService;
import com.work.university.tools.domain.AjaxResult;
import com.work.university.tools.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private PaperService paperService;

    /**
     * 获取智能组卷结果
     * @return
     */
    @PostMapping("/getSmartPaperList")
    public AjaxResult getSmartPaperList(@RequestBody RuleBean rule){
        Paper res = paperService.getSmartPaper(rule,questionService);
        List<TestQuestion> qList = res.getQuestionList();
        for(TestQuestion t : qList){
            int ts = t.getQuestionId();
            List<SingleChoose> tar = questionService.getQuestionSingleChoose(String.valueOf(ts));
            t.setDetail(tar);
        }
        res.setQuestionList(qList);
        return AjaxResult.success(res);
    }
    /**
     * TODO
     * 保存手动组卷结果
     */
    @PostMapping("/savePaper")
    public AjaxResult savePaper(@RequestBody Paper paper){

        return AjaxResult.success();
    }
    /**
     * TODO
     * 获取科目对应章节的下拉树列表
     *
     */
    @GetMapping("/getTreeSelect")
    public AjaxResult saveQuestion(@RequestParam(name = "subject",required = false)String subject){
        if(StringUtils.isNotNull(subject) && !subject.equals("")){

        }
        paperService.getChapterTree(subject);
        return AjaxResult.success();
    }

    /**
     * 保存试卷结果
     */
    @GetMapping("/savePaper")
    public AjaxResult saveQuestion(@RequestBody TestQuestion question){
        return AjaxResult.success();
    }


}
