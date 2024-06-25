package com.app.nperest.repository;

import com.app.nperest.domain.*;
import com.app.nperest.mapper.AnswerMapper;
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

    public List<AnswerDTO> selectAnswerList(Long id) {
        return answerMapper.selectAnswerList(id);
    }

    public Boolean isLike(Long answerId, Long memberId) {
        Boolean isLike = answerMapper.isLike(answerId, memberId);
        return isLike != null ? isLike : false;
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

    public void answerLikeInsert(AnswerLikeDTO answerLikeDTO) {
        answerMapper.answerLikeInsert(answerLikeDTO);
    }

    public void answerLikeUpdate(AnswerLikeDTO answerLikeDTO) {
        answerMapper.answerLikeUpdate(answerLikeDTO);
    }

    public Map<String, Object> isLikeExist(AnswerLikeDTO answerLikeDTO) {
        Map<String, Object> result = answerMapper.isLikeExist(answerLikeDTO);
        return result != null ? result : new HashMap<>();
    }

    public void answerHardDelete(Long id) {
        answerMapper.answerHardDelete(id);
    }

    public void answerLikeHardDelete(Long id) {
        answerMapper.answerLikeHardDelete(id);
    }

    public void replyHardDelete(Long id) {
        answerMapper.replyHardDelete(id);
    }

    public void replyLikeHardDelete(Long id) {
        answerMapper.replyLikeHardDelete(id);
    }

    public void insertProfanity(String comment, boolean target) {
        answerMapper.insertProfanity(comment, target);
    }
}
