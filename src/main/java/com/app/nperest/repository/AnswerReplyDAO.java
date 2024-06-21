package com.app.nperest.repository;

import com.app.nperest.domain.*;
import com.app.nperest.mapper.AnswerMapper;
import com.app.nperest.mapper.AnswerReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class AnswerReplyDAO {
    private final AnswerReplyMapper answerReplyMapper;

    public void insert(AnswerReplyVO answerReplyVO) {
        answerReplyMapper.insert(answerReplyVO);
    }

    public List<AnswerReplyDTO> selectList(AnswerReplyDTO answerReplyDTO) {
        return answerReplyMapper.selectList(answerReplyDTO);
    }

    public void update(AnswerReplyVO answerReplyVO) {
        answerReplyMapper.update(answerReplyVO);
    }

    public void delete(AnswerReplyVO answerReplyVO) {
        answerReplyMapper.delete(answerReplyVO);
    }

    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO) {
        return answerReplyMapper.selectLikeUserList(answerReplyVO);
    }

    public void replyLikeInsert(AnswerReplyVO answerReplyVO) {
        answerReplyMapper.replyLikeInsert(answerReplyVO);
    }

    public void replyLikeUpdate(AnswerReplyVO answerReplyVO) {
        answerReplyMapper.replyLikeUpdate(answerReplyVO);
    }

    public Map<String, Object> isLikeExist(AnswerReplyVO answerReplyVO) {
        Map<String, Object> result = answerReplyMapper.isLikeExist(answerReplyVO);
        return result != null ? result : new HashMap<>();
    }
    public int selectReplyCount(Long answerId){
        return answerReplyMapper.selectReplyCount(answerId);
    }
}
