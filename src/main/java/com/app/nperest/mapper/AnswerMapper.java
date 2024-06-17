package com.app.nperest.mapper;

import com.app.nperest.domain.AnswerDTO;
import com.app.nperest.domain.AnswerVO;
import com.app.nperest.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnswerMapper {
    public void insert(AnswerVO answerVO);

    public List<AnswerDTO> selectList(AnswerDTO answerDTO);

    public void update(AnswerVO answerVO);

    public void delete(AnswerVO answerVO);

    public List<MemberVO> selectLikeUserList(AnswerVO answerVO);

    public void answerLikeInsert(AnswerVO answerVO);

    public void answerLikeUpdate(AnswerVO answerVO);

    public Map<String, Object> isLikeExist(AnswerVO answerVO);
}
