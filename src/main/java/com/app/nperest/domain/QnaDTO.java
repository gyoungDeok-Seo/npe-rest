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
    private Long memberId;
    private String memberName;
    private String memberPosition;
    private List<FileVO> files;
    private List<TagVO> tags;
    private int pageSize = 10;
    private int startPage = 0;
    private int endPage = 1;
    private int flag;
    private int offset;
    private int start;
    private int answerCnt;
    private int replyCnt;
    private String returnTag;

    public void paging() {
        this.start = this.pageSize * this.startPage;
        this.offset = this.pageSize * this.endPage;
    }
}
