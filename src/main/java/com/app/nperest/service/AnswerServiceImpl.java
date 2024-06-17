package com.app.nperest.service;

import com.app.nperest.domain.AnswerDTO;
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
    public List<AnswerDTO> selectList(AnswerDTO answerDTO) {
        return answerDAO.selectList(answerDTO);
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
    public boolean answerLike(AnswerVO answerVO) {
        boolean isLikeExist = !answerDAO.isLikeExist(answerVO).isEmpty();
        if (!isLikeExist) {
            answerDAO.answerLikeInsert(answerVO);
            return true; // 답변에 좋아요를 추가했음을 나타냅니다.
        } else {
            answerDAO.answerLikeUpdate(answerVO);
            // 데이터베이스에서 갱신된 상태를 가져옵니다.
            Map<String, Object> likeStatus = answerDAO.isLikeExist(answerVO);
            Integer statusInteger = (Integer) likeStatus.get("status");
            boolean statusBoolean = statusInteger != null && statusInteger == 1;
            return statusBoolean;
        }
    }

}
