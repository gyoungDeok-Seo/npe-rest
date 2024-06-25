package com.app.nperest.repository;

import com.app.nperest.domain.*;
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

    public void insert(AnswerReplyDTO answerReplyDTO) {
        answerReplyMapper.insert(answerReplyDTO);
    }

    public List<AnswerReplyDTO> selectReplyList(Long answerId) {
        return answerReplyMapper.selectReplyList(answerId);
    }

    public Boolean isLike(Long replyId, Long memberId) {
        Boolean isLike = answerReplyMapper.isLike(replyId, memberId);
        return isLike != null ? isLike : false;
    }

    public void update(AnswerReplyDTO answerReplyDTO) {
        answerReplyMapper.update(answerReplyDTO);
    }

    public void delete(AnswerReplyDTO answerReplyDTO) {
        answerReplyMapper.delete(answerReplyDTO);
    }

    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO) {
        return answerReplyMapper.selectLikeUserList(answerReplyVO);
    }

    public void replyLikeInsert(AnswerReplyLikeDTO answerReplyLikeDTO) {
        answerReplyMapper.replyLikeInsert(answerReplyLikeDTO);
    }

    public void replyLikeUpdate(AnswerReplyLikeDTO answerReplyLikeDTO) {
        answerReplyMapper.replyLikeUpdate(answerReplyLikeDTO);
    }

    public Map<String, Object> isLikeExist(AnswerReplyLikeDTO answerReplyLikeDTO) {
        Map<String, Object> result = answerReplyMapper.isLikeExist(answerReplyLikeDTO);
        return result != null ? result : new HashMap<>();
    }

    public int selectReplyCount(Long answerId) {
        return answerReplyMapper.selectReplyCount(answerId);
    }

    public void insertProfanity(ReplyAi replyAi) {
        answerReplyMapper.insertProfanity(replyAi);
    }

    public void replyHardDelete(Long id) {
        answerReplyMapper.replyHardDelete(id);
    }

    public void replyLikeHardDelete(Long id) {
        answerReplyMapper.replyLikeHardDelete(id);
    }

    public void insertProfan(String comment, boolean target) {
        answerReplyMapper.insertProfan(comment, target);
    }
}
