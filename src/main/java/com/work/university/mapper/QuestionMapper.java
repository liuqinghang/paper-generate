package com.work.university.mapper;

import com.work.university.domain.QuestionType;
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
    public List getAllType();
}
