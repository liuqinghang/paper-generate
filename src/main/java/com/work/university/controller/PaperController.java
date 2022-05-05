package com.work.university.controller;

import com.work.university.domain.RuleBean;
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
     * paper中应该包含 组卷的所有试题以及对应的难度(可自己设定)
     *      需要的参数有
     */
    @PostMapping("/savePaper")
    public AjaxResult savePaper(@RequestBody Paper paper){
        paper.setTotalScore(paper.getTotalScore());
        paper.setDifficulty(paper.getDifficulty());
        paper.setContent(paper.getContent());
        paperService.savePaper(paper);
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
     * 获取组卷
     */
    @GetMapping("/getPaper")
    public AjaxResult getPaper(@RequestParam(name = "userId",required = false)Integer userId,
                               @RequestParam(name = "paperName",required = false)String paperName,
                               @RequestParam(name = "paperId",required = false)Integer paperId){
        Paper paper = new Paper();
        if(paperId != null) {
            paper.setPaperId(paperId);
        }
        if(StringUtils.isNotNull(paperName) && !"".equals(paperName)) {
            paper.setPaperName(paperName);
        }
        if(userId != null) {
            paper.setUserId(userId);
        }
        List<Paper> res = paperService.getPaper(paper);
        return AjaxResult.success(res);
    }


}
