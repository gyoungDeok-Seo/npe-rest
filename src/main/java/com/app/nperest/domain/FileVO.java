package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class FileVO extends Period {
    private Long id;
    private String fileName;
    private String filePath;
    private Long questionId;
}
