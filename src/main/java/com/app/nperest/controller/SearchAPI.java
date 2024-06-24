package com.app.nperest.controller;

import com.app.nperest.domain.*;
import com.app.nperest.service.SearchMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/searches/api")
@RequiredArgsConstructor
@Tag(name = "Search", description = "Search API")
public class SearchAPI {
    private final SearchMemberService searchMemberService;

    @GetMapping("/member-profile-list")
    public List<SearchMemberDTO> getMemberProfileList(Search search, Pagination pagination) {
        return searchMemberService.getSearchMemberByKeyword(search, pagination);
    }

    @GetMapping("/question-list")
    public List<QuestionDTO> getQuestionList(Search search, Pagination pagination) {
        return searchMemberService.getSearchQuestionByKeyword(search, pagination);
    }

    @GetMapping("/question-top-ten")
    public List<QnaDTO> getQuestionTopTen(){
        return searchMemberService.getQuestionTopTen();
    }
}
