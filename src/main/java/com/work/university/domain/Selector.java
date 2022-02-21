package com.work.university.domain;

import lombok.Data;
import org.springframework.context.annotation.Description;


/**
 * @Description : 下拉框包装类
 * @author 路瞳
 * @date
 */
@Data
public class Selector {
    /**
     * 标签内容
     */
    public String selectLabel;
    /**
     * 唯一标识
     */
    public String selectKey;
    /**
     * 标签值
     */
    public String selectValue;
}
