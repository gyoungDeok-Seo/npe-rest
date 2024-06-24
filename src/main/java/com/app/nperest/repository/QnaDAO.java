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

    public void updateQna(QnaDTO qnaDTO) {
        qnaMapper.updateQna(qnaDTO);
    }

    public void deleteQna(Long id) {
        qnaMapper.deleteQna(id);
    }


    public void insertFile(FileVO fileVO) {
        qnaMapper.insertFile(fileVO);
    }

    public void insertTag(TagVO tagVO) {
        qnaMapper.insertTag(tagVO);
    }

    public void deleteTag(Long id) {
        qnaMapper.deleteTag(id);
    }

    public void updateFile(FileVO fileVO) {
        qnaMapper.updateFile(fileVO);
    }

    public void updateTag(TagVO tagVO) {
        qnaMapper.updateTag(tagVO);
    }

    public List<QnaDTO> selectQnaList(List<String> tags, String category, Pagination pagination) {
        return qnaMapper.selectQnaList(tags, category, pagination);
    }

    public List<QnaDTO> selectTagQnaList(String tag, Pagination pagination) {
        return qnaMapper.selectTagQnaList(tag, pagination);
    }

    public QnaDetailDTO selectQnaDetail(Long id) {
        return qnaMapper.selectQnaDetail(id);
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

    public void incrementHits(Long id) {
        qnaMapper.incrementHits(id);

    }

    public int tagQnaListCount(String tag) {
        return qnaMapper.tagQnaListCount(tag);
    }
}
