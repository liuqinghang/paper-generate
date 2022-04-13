package com.work.university.domain.question;

import lombok.Data;

/**
 * 单选选项
 * @author 路瞳
 */
@Data
public class SingleChoose {

    public int choosedId;

    public int questionId;

    public String choosedContent;
}
