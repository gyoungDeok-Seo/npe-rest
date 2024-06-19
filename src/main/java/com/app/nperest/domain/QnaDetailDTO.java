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
public class QnaDetailDTO extends Period implements Serializable {
    private Long id;
    private String questionTitle;
    private String questionContent;
    private boolean status;
    private String categoryName;
    private String categoryValue;
    private Long memberId;
    private boolean creater;
}
