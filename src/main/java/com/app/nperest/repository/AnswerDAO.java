package com.app.nperest.repository;

import com.app.nperest.domain.AnswerDTO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.mapper.AnswerMapper;
import com.app.nperest.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AnswerDAO {
    private final AnswerMapper answerMapper;

    public void insert(AnswerVO answerVO) {
        answerMapper.insert(answerVO);
    }

    public List<AnswerDTO> selectList(AnswerDTO answerDTO) {
        return answerMapper.selectList(answerDTO);
    }

    public void update(AnswerVO answerVO) {
        answerMapper.update(answerVO);
    }

    public void delete(AnswerVO answerVO) {
        answerMapper.delete(answerVO);
    }

    public List<MemberVO> selectLikeUserList(AnswerVO answerVO) {
        return answerMapper.selectLikeUserList(answerVO);
    }

    public void answerLikeInsert(AnswerVO answerVO) {
        answerMapper.answerLikeInsert(answerVO);
    }

    public void answerLikeUpdate(AnswerVO answerVO) {
        answerMapper.answerLikeUpdate(answerVO);
    }

    public Map<String, Object> isLikeExist(AnswerVO answerVO) {
        Map<String, Object> result = answerMapper.isLikeExist(answerVO);
        return result != null ? result : new HashMap<>();
    }

}
