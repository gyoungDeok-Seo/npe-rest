package com.app.nperest.mapper;

import com.app.nperest.domain.CategoryVO;
import com.app.nperest.domain.FileVO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.TagVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QnaMapperTests {
    @Autowired
    private QnaMapper qnaMapper;

    @Test
    public void insertTest() {
        QnaDTO qnaDTO = new QnaDTO();
        qnaDTO.setQuestionTitle("제목");
        qnaDTO.setQuestionContent("내용");
        qnaDTO.setStatus(true);
        qnaDTO.setCategoryId(1L);
        qnaDTO.setMemberId(3L);

        qnaMapper.insertQna(qnaDTO);

        FileVO fileVO = new FileVO();
        fileVO.setFileName("파일이름");
        fileVO.setFilePath("파일경로");
        fileVO.setQuestionId(3L);

        qnaMapper.insertFile(fileVO);

        TagVO tagVO = new TagVO();
        tagVO.setTagName("태그이름");
        tagVO.setStatus(true);
        tagVO.setQuestionId(3L);

        qnaMapper.insertTag(tagVO);

    }

    @Test
    public void selectCategoryListTest() {
        System.out.println(qnaMapper.selectCategoryList());
    }

    @Test
    public void selectTopTenTest() {
        System.out.println(qnaMapper.selectTopTen());
    }
}
