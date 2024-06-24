package com.app.nperest.service;

import com.app.nperest.domain.FileVO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@Slf4j
public class QnaServiceTests {

    @Autowired
    private QnaService qnaService;

    @Test
    public void insertTest() {
        QnaDTO qnaDTO = new QnaDTO();
        qnaDTO.setQuestionTitle("제목");
        qnaDTO.setQuestionContent("내용");
        qnaDTO.setStatus(true);
        qnaDTO.setCategoryId(1L);
        qnaDTO.setMemberId(3L);

        List<FileVO> files = new ArrayList<>();
        FileVO file1 = new FileVO();
        file1.setFileName("파일이름1");
        file1.setFilePath("파일경로1");
        file1.setQuestionId(3L);

        FileVO file2 = new FileVO();  // 올바른 변수명으로 수정
        file2.setFileName("파일이름2"); // file2 설정 부분 수정
        file2.setFilePath("파일경로2");
        file2.setQuestionId(3L);

        files.add(file1);
        files.add(file2);

        qnaDTO.setFiles(files);

        List<TagVO> tags = new ArrayList<>();
        TagVO tag1 = new TagVO();
        tag1.setTagName("태그1");
        tag1.setStatus(true);
        tag1.setQuestionId(3L);

        TagVO tag2 = new TagVO();
        tag2.setTagName("태그2"); // 태그 이름 수정
        tag2.setStatus(true);
        tag2.setQuestionId(3L);

        tags.add(tag1);
        tags.add(tag2);

        qnaDTO.setTags(tags);

        qnaService.insert(qnaDTO);
    }

    @Test
    public void selectCategoryListTest() {
        System.out.println(qnaService.selectCategoryList());
    }

    @Test
    public void selectTopTenTest() {
        System.out.println(qnaService.selectTopTen());
    }

    @Test
    public void selectBestAnswerTest() {
        System.out.println(qnaService.selectBestAnswer());
    }

    @Test
    public void tagQnaListCountTest() {
        int count = qnaService.tagQnaListCount("react");
        System.out.println(count);
    }
}
