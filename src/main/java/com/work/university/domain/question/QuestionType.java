package com.work.university.domain.question;

import lombok.Data;
import org.springframework.context.annotation.Bean;

/**
 * @author 路瞳
 */

@Data
public class QuestionType {

    private int type;

    private String typeName;

    private String remark;

}
