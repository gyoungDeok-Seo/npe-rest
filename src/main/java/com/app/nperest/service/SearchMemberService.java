package com.app.nperest.service;

import com.app.nperest.domain.*;

import java.util.List;

public interface SearchMemberService {
    public List<SearchMemberDTO> getSearchMemberByKeyword(Search search, Pagination pagination);
    public List<QuestionDTO> getSearchQuestionByKeyword(Search search, Pagination pagination);
    public List<QnaDTO> getQuestionTopTen();
}
