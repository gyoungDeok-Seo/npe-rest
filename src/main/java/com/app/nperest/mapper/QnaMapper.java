package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
    public void insertQna(QnaDTO qnaDTO);

    public void updateQna(QnaDTO qnaDTO);

    public void deleteQna(Long id);

    public void insertFile(FileVO fileVO);

    public void updateFile(FileVO fileVO);

    public void insertTag(TagVO tagVO);

    public void updateTag(TagVO tagVO);

    public void deleteTag(Long id);

    // qna 리스트
    public List<QnaDTO> selectQnaList(List<String> tags, String categoryValue, Pagination pagination);

    public List<QnaDTO> selectTagQnaList(String tag, Pagination pagination);

    public QnaDetailDTO selectQnaDetail(Long id);

    // 메인 화면에 카테고리 리스트
    public List<CategoryVO> selectCategoryList();

    // 오늘의 질문 top10
    public List<QnaDTO> selectTopTen();

    // 답변 베스트
    public List<QnaDTO> selectBestAnswer();

    public void incrementHits(Long id);

    public int tagQnaListCount(String tag);
}
