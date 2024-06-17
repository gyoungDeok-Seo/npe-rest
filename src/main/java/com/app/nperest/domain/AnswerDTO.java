package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class AnswerDTO {
    private Long id;
    private String answerContent;
    private boolean status;
    private Long memberId;
    private String memberPosition;
    private String kakaoProfileUrl;
    private int likeCnt;
    private int replyCnt;
    private boolean memberLiked;
    private String memberName;
    private Long questionId;
    private String flag;
    private List<AnswerReplyDTO> replyList;
}
