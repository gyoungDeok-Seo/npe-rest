package com.app.nperest.service;

import com.app.nperest.domain.CategoryVO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.QnaDetailDTO;

import java.util.List;

public interface QnaService {
    public void insert(QnaDTO qnaDTO);

    public List<QnaDTO> selectQnaList();

    public QnaDetailDTO selectDetail(QnaDetailDTO qnaDetailDTO);

    //메인 화면 카테고리 리스트
    public List<CategoryVO> selectCategoryList();

    public List<QnaDTO> selectTopTen();

    public List<QnaDTO> selectBestAnswer();
}
