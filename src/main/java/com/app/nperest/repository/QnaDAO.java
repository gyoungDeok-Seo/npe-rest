package com.app.nperest.repository;

import com.app.nperest.domain.FileDTO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaDAO {
    private final QnaMapper qnaMapper;

    public int insertQna(QnaDTO qnaDTO) {
        return qnaMapper.insertQna(qnaDTO);
    }

    public int insertFile(FileDTO fileDTO) {
        return qnaMapper.insertFile(fileDTO);
    }
}
