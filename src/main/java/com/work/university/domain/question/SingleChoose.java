package com.work.university.domain.question;

import lombok.Data;

/**
 * 单选选项
 * @author 路瞳
 */
@Data
public class SingleChoose {

    private int choosedId;

    private int questionId;

    private String choosedContent;
}
