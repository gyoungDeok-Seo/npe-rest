package com.app.nperest.repository;

import com.app.nperest.domain.FileVO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.TagVO;
import com.app.nperest.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaDAO {
    private final QnaMapper qnaMapper;

    public void insertQna(QnaDTO qnaDTO) {
        ;
    }

    public void insertFile(FileVO fileVO) {
        ;
    }

    public void insertTag(TagVO tagVO) {
        ;
    }
}
