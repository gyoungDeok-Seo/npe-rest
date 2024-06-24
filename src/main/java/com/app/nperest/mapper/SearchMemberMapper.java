package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMemberMapper {
    public List<SearchMemberDTO> selectSearchMember(Search search, Pagination pagination);
    public List<QuestionDTO> selectSearchQuestion(Search search, Pagination pagination);
    public List<QnaDTO> selectQuestionTopTen();
}
