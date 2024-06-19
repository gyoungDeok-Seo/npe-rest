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
    public Map<String, Object> getSessionInfo(HttpSession session, Long memberId) {
        MemberVO sessionMember = (MemberVO) session.getAttribute("member");

        Optional<MemberVO> foundMember = memberService.getMemberById(memberId);
        MemberVO member = foundMember.get();

        Map<String, Object> response = new HashMap<>();
        boolean same = sessionMember.getId().equals(member.getId());
        response.put("member", member);
        response.put("same", same);
        if(sessionMember != null){
            response.put("loggedIn", true);
        }else {
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
    public List<MemberSkillDTO> getMemberSkill(Long memberId) {
//        MemberVO member = (MemberVO) session.getAttribute("member");

        return memberService.getMemberSkill(memberId);
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
    public List<MyQuestionDTO> getMyQuestions(Long memberId, Pagination pagination) {
//        MemberVO member = (MemberVO) session.getAttribute("member");

        return memberService.getMyQuestions(memberId, pagination);
    }

    @GetMapping("/my-answer-list")
    public List<MyAnswerDTO> getMyAnswers(Long memberId, Pagination pagination) {
//        MemberVO member = (MemberVO) session.getAttribute("member");

        return memberService.getMyAnswers(memberId, pagination);
    }

    @GetMapping("/answer-like")
    public boolean getAnswerLike(HttpSession session, Long answerId){
        boolean response = true;
        MemberVO member = (MemberVO) session.getAttribute("member");
        Optional<AnswerLikeVO> foundAnswerLike = memberService.getAnswerLike(member.getId(), answerId);

        if(foundAnswerLike.isEmpty()){
            response = false;
        } else if(!foundAnswerLike.get().isStatus()) {
            response = false;
        }
        return response;
    }

    @PatchMapping("/answer-like-modify")
    public Map<String, Object> modifyAnswerLike(HttpSession session, @RequestParam Long answerId) {
        AnswerLikeVO answerLikeVO = new AnswerLikeVO();
        Map<String, Object> response = new HashMap<>();
        MemberVO member = (MemberVO) session.getAttribute("member");
        Optional<AnswerLikeVO> foundAnswerLike = memberService.getAnswerLike(member.getId(), answerId);

        if(foundAnswerLike.isPresent()) {
            answerLikeVO = foundAnswerLike.get();
            answerLikeVO.setStatus(!answerLikeVO.isStatus());
            memberService.modifyAnswerLike(answerLikeVO);
        } else {
            answerLikeVO.setMemberId(member.getId());
            answerLikeVO.setAnswerId(answerId);
            memberService.creatAnswerLike(answerLikeVO);
        }

        int answerLikeCount = memberService.getAnswerLikeCount(member.getId(), answerId);
        foundAnswerLike = memberService.getAnswerLike(member.getId(), answerId);

        response.put("answerLikeCount", answerLikeCount);
        response.put("answerLikeVO", foundAnswerLike.get());

        return response;
    }

    @GetMapping("/career-list")
    public List<CareerDTO> getCareerList(Long memberId) {
        return memberService.getCareerByMemberId(memberId);
    }

    @PostMapping("/create-career")
    public void createCareer(@RequestBody CareerDTO careerDTO) {
//        System.out.println(careerDTO.getCareerSkills());
        memberService.createCareer(careerDTO);
    }
}
