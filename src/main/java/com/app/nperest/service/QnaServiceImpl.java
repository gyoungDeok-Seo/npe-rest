package com.app.nperest.service;

import com.app.nperest.domain.*;
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
        // 1. Q&A 정보 저장
        qnaDAO.insertQna(qnaDTO); // QnaDTO를 먼저 저장하여 id를 생성

        // QnaDTO에 저장된 id를 가져와서 file과 tag의 questionId 설정
        System.out.println(qnaDTO);
        // 2. 파일 정보 저장
        List<FileVO> files = qnaDTO.getFiles();
        if (files != null && !files.isEmpty()) {
            files.forEach(file -> {
                file.setQuestionId(qnaDTO.getId()); // 파일과 질문의 관계 설정
                qnaDAO.insertFile(file); // DAO를 통해 파일 정보 저장
            });
        }

        // 3. 태그 정보 저장
        List<TagVO> tags = qnaDTO.getTags();
        if (tags != null && !tags.isEmpty()) {
            tags.forEach(tag -> {
                tag.setQuestionId(qnaDTO.getId()); // 태그와 질문의 관계 설정
                qnaDAO.insertTag(tag); // DAO를 통해 태그 정보 저장
            });
        }
    }

    @Override
    public List<QnaDTO> selectQnaList() {
        return qnaDAO.selectQnaList();
    }

    @Override
    public QnaDetailDTO selectDetail(QnaDetailDTO qnaDetailDTO) {
        return qnaDAO.selectDetail(qnaDetailDTO);
    }

    @Override
    public List<CategoryVO> selectCategoryList() {
        return qnaDAO.selectCategoryList();
    }

    @Override
    public List<QnaDTO> selectTopTen() {
        return qnaDAO.selectTopTen();
    }

    @Override
    public List<QnaDTO> selectBestAnswer() {
        return qnaDAO.selectBestAnswer();
    }

}
