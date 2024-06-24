package com.app.nperest.repository;

import com.app.nperest.domain.*;
import com.app.nperest.mapper.SearchMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchMemberDAO {
    private final SearchMemberMapper searchMapper;
    public List<SearchMemberDTO> findSearchMember(Search search, Pagination pagination){
        return searchMapper.selectSearchMember(search, pagination);
    };
    public List<QuestionDTO> findSearchQuestion(Search search, Pagination pagination){
        return searchMapper.selectSearchQuestion(search, pagination);
    };
    public List<QnaDTO> findQuestionTopTen(){
        return searchMapper.selectQuestionTopTen();
    };
}
