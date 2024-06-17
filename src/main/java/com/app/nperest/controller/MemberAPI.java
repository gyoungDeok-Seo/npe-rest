package com.app.nperest.controller;

import com.app.nperest.domain.*;
import com.app.nperest.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/members/api")
@RequiredArgsConstructor
// http://localhost:10000/swagger-ui/index.html
@Tag(name = "Member", description = "Member API")
public class MemberAPI {
    private final MemberService memberService;

    @GetMapping("/session")
    public Map<String, Object> getSessionInfo(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        MemberVO member = (MemberVO) session.getAttribute("member");
        if (member != null) {
            response.put("loggedIn", true);
            response.put("member", member);
        } else {
            response.put("loggedIn", false);
        }

        return response;
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/members/api/session");
    }

    @PatchMapping("/member-info/modify")
    public void modifyMemberInfo(@RequestBody MemberVO memberVO, HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        member.setMemberName(memberVO.getMemberName());
        member.setMemberPosition(memberVO.getMemberPosition());
        member.setMemberIntro(memberVO.getMemberIntro());
        memberService.modifyMemberInfo(member);
    }

    @GetMapping("/skill")
    public List<MemberSkillDTO> getMemberSkill(HttpSession session) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        String kakaoEmail = member.getKakaoEmail();

        return memberService.getMemberSkill(kakaoEmail);
    }

    @PostMapping("/skill")
    public void modifyMemberSkill(@RequestBody SkillModifyInfo skillModifyInfo, HttpSession session) {
        MemberSkillDTO memberSkillDTO = new MemberSkillDTO();
        MemberVO member = (MemberVO) session.getAttribute("member");

        for (SkillVO skillVO : skillModifyInfo.getAddList()) {
            memberSkillDTO.setMemberId(member.getId());
            memberSkillDTO.setSkillId(skillVO.getId());
            memberService.saveMemberSkill(memberSkillDTO);
        }

        for (SkillVO skillVO : skillModifyInfo.getRemoveList()) {
            memberSkillDTO.setMemberId(member.getId());
            memberSkillDTO.setSkillId(skillVO.getId());
            memberService.dropMemberSkill(memberSkillDTO);
        }
    }

    @GetMapping("/skillSearch")
    public List<SkillVO> getSkillSearch(@RequestParam String keyword) {
        Search search = new Search();
        search.setKeyword(keyword);

        return memberService.getSearchSkillList(search);
    }

    @GetMapping("/my-question-list")
    public List<MyQuestionDTO> getMyQuestions(HttpSession session, Pagination pagination) {
        MemberVO member = (MemberVO) session.getAttribute("member");

        return memberService.getMyQuestions(member.getId(), pagination);
    }

    @GetMapping("/my-answer-list")
    public List<MyAnswerDTO> getMyAnswers(HttpSession session, Pagination pagination) {
        MemberVO member = (MemberVO) session.getAttribute("member");

        return memberService.getMyAnswers(member.getId(), pagination);
    }

    @GetMapping("/answer-like-count")
    public int getAnswerLikeCount(HttpSession session, Long answerId){
        MemberVO member = (MemberVO) session.getAttribute("member");

        return memberService.getAnswerLikeCount(member.getId(), answerId);
    }

    @PatchMapping("/answer-like-modify")
    public void modifyAnswerLike(HttpSession session, Long answerId) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        Optional<AnswerLikeVO> foundAnswerLike = memberService.getAnswerLike(member.getId(), answerId);
        if(foundAnswerLike.isPresent()) {
            AnswerLikeVO answerLikeVO = foundAnswerLike.get();
            answerLikeVO.setStatus(!answerLikeVO.isStatus());
            memberService.modifyAnswerLike(answerLikeVO);
        } else {
            AnswerLikeVO answerLikeVO = new AnswerLikeVO();
            answerLikeVO.setAnswerId(member.getId());
            answerLikeVO.setMemberId(answerId);
            memberService.creatAnswerLike(answerLikeVO);
        }
    }
}
