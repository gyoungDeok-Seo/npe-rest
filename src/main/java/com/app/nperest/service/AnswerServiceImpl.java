package com.app.nperest.service;

import com.app.nperest.domain.AnswerDTO;
import com.app.nperest.domain.AnswerLikeDTO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.repository.AnswerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnswerServiceImpl implements AnswerService {
    private final AnswerDAO answerDAO;

    @Override
    public void insert(AnswerVO answerVO) {
        answerDAO.insert(answerVO);
    }

    @Override
    public List<AnswerDTO> selectAnswerList(Long id) {
        return answerDAO.selectAnswerList(id);
    }

    @Override
    public Boolean isLike(Long answerId, Long memberId) {
        Boolean isLike = answerDAO.isLike(answerId, memberId);
        return isLike != null ? isLike : false;
    }

    @Override
    public void update(AnswerVO answerVO) {
        answerDAO.update(answerVO);
    }

    @Override
    public void delete(AnswerVO answerVO) {
        answerDAO.delete(answerVO);
    }

    @Override
    public List<MemberVO> selectLikeUserList(AnswerVO answerVO) {
        return answerDAO.selectLikeUserList(answerVO);
    }

    @Override
    public void answerLike(AnswerLikeDTO answerLikeDTO) {
        boolean isLikeExist = !answerDAO.isLikeExist(answerLikeDTO).isEmpty();
        if (!isLikeExist) {
            answerDAO.answerLikeInsert(answerLikeDTO);
        } else {
            answerDAO.answerLikeUpdate(answerLikeDTO);

        }
    }

    @Override
    public void answerHardDelete(Long id) {
        answerDAO.answerHardDelete(id);
    }

    @Override
    public void answerLikeHardDelete(Long id) {
        answerDAO.answerLikeHardDelete(id);
    }

    @Override
    public void replyHardDelete(Long id) {
        answerDAO.replyHardDelete(id);
    }

    @Override
    public void replyLikeHardDelete(Long id) {
        answerDAO.replyLikeHardDelete(id);
    }

    @Override
    public void insertProfanity(String comment, boolean target) {
        answerDAO.insertProfanity(comment, target);
    }
}
