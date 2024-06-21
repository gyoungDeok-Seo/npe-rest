package com.app.nperest.service;

import com.app.nperest.domain.*;

import java.util.List;

public interface AnswerReplyService {
    public void insert(AnswerReplyVO answerReplyVO);

    public List<AnswerReplyDTO> selectList(AnswerReplyDTO answerReplyDTO);

    public void update(AnswerReplyVO answerReplyVO);

    public void delete(AnswerReplyVO answerReplyVO);

    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO);

    public boolean replyLike(AnswerReplyVO answerReplyVO);

    public int selectReplyCount(Long answerId);
}
