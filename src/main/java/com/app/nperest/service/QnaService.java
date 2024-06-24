package com.app.nperest.service;

import com.app.nperest.domain.*;

import java.util.List;

public interface QnaService {
    public void insert(QnaDTO qnaDTO);

    public void update(QnaDTO qnaDTO);

    public void delete(Long id);

    public List<QnaDTO> selectQnaList(List<String> tags, String category, Pagination pagination);

    public List<QnaDTO> selectTagQnaList(String tag, Pagination pagination);

    public QnaDetailDTO selectQnaDetail(Long id);

    //메인 화면 카테고리 리스트
    public List<CategoryVO> selectCategoryList();

    public List<QnaDTO> selectTopTen();

    public List<QnaDTO> selectBestAnswer();

    public void incrementHits(Long id);

    public int tagQnaListCount(String tag);
}
