package com.app.nperest.repository;

import com.app.nperest.domain.*;
import com.app.nperest.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QnaDAO {
    private final QnaMapper qnaMapper;

    public void insertQna(QnaDTO qnaDTO) {
        qnaMapper.insertQna(qnaDTO);
    }

    public void insertFile(FileVO fileVO) {
        qnaMapper.insertFile(fileVO);
    }

    public void insertTag(TagVO tagVO) {
        qnaMapper.insertTag(tagVO);
    }

    public List<QnaDTO> selectQnaList(){
         return qnaMapper.selectQnaList();
    }

    public QnaDetailDTO selectDetail(QnaDetailDTO qnaDetailDTO) {
        return qnaMapper.selectDetail(qnaDetailDTO);
    }

    public List<CategoryVO> selectCategoryList() {
        return qnaMapper.selectCategoryList();
    }

    public List<QnaDTO> selectTopTen() {
        return qnaMapper.selectTopTen();
    }

    public List<QnaDTO> selectBestAnswer() {
        return qnaMapper.selectBestAnswer();
    }
}
