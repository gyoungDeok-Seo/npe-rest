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
import java.util.Map;

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

}
