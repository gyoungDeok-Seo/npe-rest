package com.app.nperest.service;

import com.app.nperest.domain.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FileServiceTests {
    @Autowired
    private FileService fileService;

    @Test
    public void selectFileListTest() {
        List<FileVO> fileList = fileService.selectFileList(1L);
        System.out.println(fileList);
    }
}
