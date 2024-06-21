package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
    public void insertQna(QnaDTO qnaDTO);

    public void insertFile(FileVO fileVO);

    public void insertTag(TagVO tagVO);
    // qna 리스트
    public List<QnaDTO> selectQnaList();

    public QnaDetailDTO selectDetail(QnaDetailDTO qnaDetailDTO);
    // 메인 화면에 카테고리 리스트
    public List<CategoryVO> selectCategoryList();
    // 오늘의 질문 top10
    public List<QnaDTO> selectTopTen();
    // 답변 베스트
    public List<QnaDTO> selectBestAnswer();

}
