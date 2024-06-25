package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnswerReplyMapper {
    public void insert(AnswerReplyDTO answerReplyDTO);

    public List<AnswerReplyDTO> selectReplyList(Long answerId);

    public Boolean isLike(Long replyId, Long memberId);

    public void update(AnswerReplyDTO answerReplyDTO);

    public void delete(AnswerReplyDTO answerReplyDTO);

    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO);

    public void replyLikeInsert(AnswerReplyLikeDTO answerReplyLikeDTO);

    public void replyLikeUpdate(AnswerReplyLikeDTO answerReplyLikeDTO);

    public Map<String, Object> isLikeExist(AnswerReplyLikeDTO answerReplyLikeDTO);

    public int selectReplyCount(Long answerId);

    public void insertProfanity(ReplyAi replyAi);

    public void replyHardDelete(Long id);

    public void replyLikeHardDelete(Long id);

    public void insertProfan(String comment, boolean target);
}
