package com.app.nperest.service;

import com.app.nperest.domain.*;
import com.app.nperest.repository.MemberDAO;
import com.app.nperest.repository.SearchMemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Primary
public class SearchMemberServiceImpl implements SearchMemberService {
    private final SearchMemberDAO searchMemberDAO;
    private final MemberDAO memberDAO;

    @Override
    public List<SearchMemberDTO> getSearchMemberByKeyword(Search search, Pagination pagination) {
        pagination.progress(5);

        List<SearchMemberDTO> searchMemberList = searchMemberDAO.findSearchMember(search, pagination);
        for(SearchMemberDTO searchMember: searchMemberList) {
            searchMember.setCareers(memberDAO.findCareerByMemberId(searchMember.getId()));
        }
        return searchMemberList;
    }

    @Override
    public List<QuestionDTO> getSearchQuestionByKeyword(Search search, Pagination pagination) {
        pagination.progress(20);

        List<QuestionDTO> searchQuestionList = searchMemberDAO.findSearchQuestion(search, pagination);
        for(QuestionDTO searchQuestion: searchQuestionList) {
            searchQuestion.setAnswerCount(memberDAO.countAnswerCountForQuestion(searchQuestion.getId()));
        }
        return searchQuestionList;
    }

    @Override
    public List<QnaDTO> getQuestionTopTen() {
        return searchMemberDAO.findQuestionTopTen();
    }


}
