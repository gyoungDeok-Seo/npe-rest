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

    public List<QnaDTO> selectList(QnaDTO qnaDTO) {
        return qnaMapper.selectList(qnaDTO);
    }

    public QnaDetailDTO selectDetail(QnaDetailDTO qnaDetailDTO) {
        return qnaMapper.selectDetail(qnaDetailDTO);
    }


}
