package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class AnswerReplyDTO extends Period implements Serializable {
    private Long id;
    private String replayContent;
    private boolean status;
    private Long memberId;
    private String memberName;
    private String memberPosition;
    private String kakaoProfileUrl;
    private int likeCnt;
    private boolean memberLiked;
    private boolean master;
    private Long answerId;
    private Long questionId;

}
