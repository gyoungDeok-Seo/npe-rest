package com.app.nperest.service;

import com.app.nperest.domain.*;
import com.app.nperest.repository.AnswerReplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnswerReplyServiceImpl implements AnswerReplyService {
    private final AnswerReplyDAO answerReplyDAO;

    @Override
    public void insert(AnswerReplyDTO answerReplyDTO) {
        answerReplyDAO.insert(answerReplyDTO);
    }

    @Override
    public List<AnswerReplyDTO> selectReplyList(Long answerId, Long memberId) {
        List<AnswerReplyDTO> list = answerReplyDAO.selectReplyList(answerId);
        for (AnswerReplyDTO answerReplyDTO : list) {
            answerReplyDTO.setMemberLiked(answerReplyDAO.isLike(answerReplyDTO.getId(), memberId));
            answerReplyDTO.setMaster(Objects.equals(answerReplyDTO.getMemberId(), memberId));
        }
        return list;
    }

    @Override
    public Boolean isLike(Long replyId, Long memberId) {
        Boolean isLike = answerReplyDAO.isLike(replyId, memberId);
        return isLike != null ? isLike : false;
    }

    @Override
    public void update(AnswerReplyDTO answerReplyDTO) {
        answerReplyDAO.update(answerReplyDTO);
    }

    @Override
    public void delete(AnswerReplyDTO answerReplyDTO) {
        answerReplyDAO.delete(answerReplyDTO);
    }

    @Override
    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO) {
        return answerReplyDAO.selectLikeUserList(answerReplyVO);
    }

    @Override
    public void replyLike(AnswerReplyLikeDTO answerReplyLikeDTO) {
        boolean isLikeExist = !answerReplyDAO.isLikeExist(answerReplyLikeDTO).isEmpty();
        if (!isLikeExist) {
            answerReplyDAO.replyLikeInsert(answerReplyLikeDTO);
        } else {
            answerReplyDAO.replyLikeUpdate(answerReplyLikeDTO);

        }
    }

    @Override
    public int selectReplyCount(Long answerId) {
        return answerReplyDAO.selectReplyCount(answerId);
    }

    @Override
    public void insertProfanity(ReplyAi replyAi) {
        answerReplyDAO.insertProfanity(replyAi);
    }

    @Override
    public void replyHardDelete(Long id) {
        answerReplyDAO.replyHardDelete(id);
    }

    @Override
    public void replyLikeHardDelete(Long id) {
        answerReplyDAO.replyLikeHardDelete(id);
    }

    @Override
    public void insertProfan(String comment, boolean target) {
        answerReplyDAO.insertProfan(comment, target);
    }
}



