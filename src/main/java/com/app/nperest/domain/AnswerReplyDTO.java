package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class AnswerReplyDTO {
    private Long id;
    private String replayContent;
    private boolean status;
    private Long memberId;
    private Long answerId;
    private String memberPosition;
    private String kakaoProfileUrl;
    private int likeCnt;
    private boolean memberLiked;
    private String memberName;

}
