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
public class QnaDTO extends Period {
    private int id;
    private String questionTitle;
    private String questionContent;
    private boolean status;
    private Long categoryId;
    private Long memberId;
    private List<FileDTO> files;
}
