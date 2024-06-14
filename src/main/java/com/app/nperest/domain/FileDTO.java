package com.app.nperest.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString(callSuper = true)
public class FileDTO {
    private Long id;
    private Long questionId;
    private String fileName;
    private String filePath;
}
