package com.app.nperest.service;

import com.app.nperest.domain.AnswerReplyDTO;
import com.app.nperest.domain.AnswerReplyVO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.repository.AnswerReplyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnswerReplyServiceImpl implements AnswerReplyService {
    private final AnswerReplyDAO answerReplyDAO;

    @Override
    public void insert(AnswerReplyVO answerReplyVO) {
        answerReplyDAO.insert(answerReplyVO);
    }

    @Override
    public List<AnswerReplyDTO> selectList(AnswerReplyDTO answerReplyDTO) {
        return answerReplyDAO.selectList(answerReplyDTO);
    }

    @Override
    public void update(AnswerReplyVO answerReplyVO) {
        answerReplyDAO.update(answerReplyVO);
    }

    @Override
    public void delete(AnswerReplyVO answerReplyVO) {
        answerReplyDAO.delete(answerReplyVO);
    }

    @Override
    public List<MemberVO> selectLikeUserList(AnswerReplyVO answerReplyVO) {
        return answerReplyDAO.selectLikeUserList(answerReplyVO);
    }

    @Override
    public boolean replyLike(AnswerReplyVO answerReplyVO) {
        boolean isLikeExist = !answerReplyDAO.isLikeExist(answerReplyVO).isEmpty();
        if (!isLikeExist) {
            answerReplyDAO.replyLikeInsert(answerReplyVO);
            return true; // 답변에 좋아요를 추가했음을 나타냅니다.
        } else {
            answerReplyDAO.replyLikeUpdate(answerReplyVO);
            // 데이터베이스에서 갱신된 상태를 가져옵니다.
            Map<String, Object> likeStatus = answerReplyDAO.isLikeExist(answerReplyVO);
            Integer statusInteger = (Integer) likeStatus.get("status");
            boolean statusBoolean = statusInteger != null && statusInteger == 1;
            return statusBoolean;
        }
    }

    @Override
    public int selectReplyCount(Long answerId) {
        return answerReplyDAO.selectReplyCount(answerId);
    }
}

