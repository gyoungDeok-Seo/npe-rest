package com.app.nperest.service;

import com.app.nperest.domain.FileVO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.TagVO;
import com.app.nperest.repository.QnaDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QnaServiceImpl implements QnaService {
    private final QnaDAO qnaDAO;


    @Override
    public void insert(QnaDTO qnaDTO) {
        List<FileVO> files = qnaDTO.getFiles();
        files.forEach(qnaDAO::insertFile);
        List<TagVO> tags = qnaDTO.getTags();
        tags.forEach(qnaDAO::insertTag);
        qnaDAO.insertQna(qnaDTO);
    }
}
