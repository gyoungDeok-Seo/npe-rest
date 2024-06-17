package com.app.nperest.mapper;

import com.app.nperest.domain.AnswerReplyDTO;
import com.app.nperest.domain.AnswerReplyVO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnswerReplyMapper {
    public void insert(AnswerReplyVO answerReplyVO);

    public List<AnswerReplyDTO> selectList(AnswerReplyDTO answerReplyDTO);

    public void update(AnswerReplyVO answerReplyVO);

    public void delete(AnswerReplyVO answerReplyVO);

    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO);

    public void replyLikeInsert(AnswerReplyVO answerReplyVO);

    public void replyLikeUpdate(AnswerReplyVO answerReplyVO);

    public Map<String, Object> isLikeExist(AnswerReplyVO answerReplyVO);
}
