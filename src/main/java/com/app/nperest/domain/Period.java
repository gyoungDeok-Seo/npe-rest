package com.app.nperest.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter @ToString
public class Period {
    protected String createdAt;
    protected String updatedAt;
}
