package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class AnswerVO {
    private Long id;
    private String answerContent;
    private boolean status;
    private Long memberId;
    private Long questionId;
    private String flag;

    public AnswerVO() {}

    public AnswerVO(boolean status) {
        this.status = status;
    }
}
