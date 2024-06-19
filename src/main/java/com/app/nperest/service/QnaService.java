package com.app.nperest.service;

import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.QnaDetailDTO;

import java.util.List;

public interface QnaService {
    public void insert(QnaDTO qnaDTO);

    public List<QnaDTO> selectList(QnaDTO qnaDTO);

    public QnaDetailDTO selectDetail(QnaDetailDTO qnaDetailDTO);

}
