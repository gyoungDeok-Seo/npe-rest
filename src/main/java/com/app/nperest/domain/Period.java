package com.app.nperest.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter @ToString
public class Period implements Serializable {
    protected String createdDate;
    protected String updatedDate;
}
