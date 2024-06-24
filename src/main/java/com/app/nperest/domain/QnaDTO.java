package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class QnaDTO extends Period implements Serializable {
    private Long id;
    private String questionTitle;
    private String questionContent;
    private boolean status;
    private Long categoryId;
    private String categoryName;
    private String categoryValue;
    private Long memberId;
    private String memberName;
    private String memberPosition;
    private String kakaoProfileUrl;
    private List<FileVO> files;
    private List<TagVO> tags;
    private int hits;
    private int answerCnt;
    private int replyCnt;
    private int maxLikeCnt;
    private List<AnswerVO> answerList;
    private List<AnswerReplyVO> replyList;
}
