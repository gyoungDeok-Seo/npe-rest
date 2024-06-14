package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class TagVO {
    private Long id;
    private String tagName;
    private boolean status;
    private Long questionId;
}
