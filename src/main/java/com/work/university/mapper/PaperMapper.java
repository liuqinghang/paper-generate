package com.work.university.mapper;

import com.work.university.domain.paper.Paper;
import com.work.university.domain.util.TreeSelectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 路瞳
 */
@Mapper
public interface PaperMapper {
    /**
     * 获取科目对应章节的下拉树列表
     * @param subject 根据科目获取对应的章节下拉树
     * @return
     */
    public List<TreeSelectEntity> getChapterTree(String subject);

    public void savePaper(Paper paper);

    /**
     * 获取所有满足条件的Paper
     * @param paper - paperId
     *              - paperName
     *              - createUser
     * @return
     */
    public List<Paper> getPaper(Paper paper);
}
