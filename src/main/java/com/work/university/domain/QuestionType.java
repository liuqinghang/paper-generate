package com.work.university.domain;

import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * @author 路瞳
 */

@Data
public class QuestionType {

    public int type;

    public String typeName;

    public String remark;

}
