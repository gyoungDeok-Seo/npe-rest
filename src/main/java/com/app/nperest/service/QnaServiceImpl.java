package com.app.nperest.service;

import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.FileDTO;
import com.app.nperest.repository.QnaDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class QnaServiceImpl implements QnaService {
    private final QnaDAO qnaDAO;


    @Override
    public int insert(QnaDTO qnaDTO) {
        int result = qnaDAO.insertQna(qnaDTO);

        // 파일 정보 삽입
        if (qnaDTO.getFiles() != null) {
            for (FileDTO file : qnaDTO.getFiles()) {
                file.setQuestionId((long) qnaDTO.getId()); // 게시판 ID 설정 (필요 시)
                qnaDAO.insertFile(file);
            }
        }

        return result;
    }
}
