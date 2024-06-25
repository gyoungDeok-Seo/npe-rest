package com.app.nperest.service;

import com.app.nperest.domain.*;

import java.util.List;

public interface AnswerReplyService {
    public void insert(AnswerReplyDTO answerReplyDTO);

    public List<AnswerReplyDTO> selectReplyList(Long answerId, Long memberId);

    public Boolean isLike(Long replyId, Long memberId);

    public void update(AnswerReplyDTO answerReplyDTO);

    public void delete(AnswerReplyDTO answerReplyDTO);

    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO);

    public void replyLike(AnswerReplyLikeDTO answerReplyLikeDTO);

    public int selectReplyCount(Long answerId);

    public void insertProfanity(ReplyAi replyAi);

    public void replyHardDelete(Long id);

    public void replyLikeHardDelete(Long id);

    public void insertProfan(String comment, boolean target);
}
