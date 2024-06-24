package com.app.nperest.controller;

import com.app.nperest.domain.*;
import com.app.nperest.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
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
        Map<String, Object> response = new HashMap<>();

        if(foundMember.isPresent()){
            MemberVO member = foundMember.get();
            response.put("member", member);
            boolean same = false;


            if(sessionMember != null){
                response.put("loggedIn", true);
                same = sessionMember.getId().equals(member.getId());
            }else {
                response.put("loggedIn", false);
            }
            response.put("same", same);

            return response;
        }

        response.put("member", new MemberVO());
        response.put("same", false);
        response.put("loggedIn", false);

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
    public List<QuestionDTO> getMyQuestions(Long memberId, Pagination pagination) {
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

        int answerLikeCount = memberService.getAnswerLikeCount(answerId);
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
        memberService.createCareer(careerDTO);
    }

    @PatchMapping("/update-career")
    public String updateCareer(@RequestBody CareerUpdateRequest request) {
        CareerDTO careerDTO = request.getCreateCareer();
        ModifyCareerInListInfo listInfo = request.getListInfo();

        if(!listInfo.getAddIndustryList().isEmpty()){
            for(CareerIndustryDTO addIndustry : listInfo.getAddIndustryList()) {
                memberService.createCareerIndustry(addIndustry.getCareerId(), addIndustry.getIndustryId());
            }
        }
        if(!listInfo.getRemoveIndustryList().isEmpty()){
            for(CareerIndustryDTO removeIndustry : listInfo.getRemoveIndustryList()) {
                memberService.dropCareerIndustries(removeIndustry);
            }
        }
        if(!listInfo.getAddSkillList().isEmpty()){
            for(CareerSkillDTO addSkill : listInfo.getAddSkillList()) {
                memberService.createCareerSkill(addSkill.getCareerId(), addSkill.getSkillId());
            }
        }
        if(!listInfo.getRemoveSkillList().isEmpty()){
            for(CareerSkillDTO removeSkill : listInfo.getRemoveSkillList()) {
                memberService.dropCareerSkills(removeSkill);
                memberService.dropCareerSkills(removeSkill);
            }
        }

        careerDTO.setStatus(true);
        careerDTO.setCareerIndustries(memberService.getCareerIndustryByCareerId(careerDTO.getId()));
        careerDTO.setCareerSkills(memberService.getCareerSkillByCareerId(careerDTO.getId()));
        memberService.modifyMemberCareer(careerDTO);
        return "ok";
    }

    @DeleteMapping("/delete-career")
    public String deleteCareer(@RequestBody CareerDTO careerDTO) {
        careerDTO.setStatus(false);
        if(!careerDTO.getCareerIndustries().isEmpty()){
            for(CareerIndustryDTO careerIndustryDTO : careerDTO.getCareerIndustries()){
                memberService.dropCareerIndustries(careerIndustryDTO);
            }
        }
        if(!careerDTO.getCareerSkills().isEmpty()){
            for(CareerSkillDTO careerSkillDTO : careerDTO.getCareerSkills()){
                memberService.dropCareerSkills(careerSkillDTO);
            }
        }
        memberService.modifyMemberCareer(careerDTO);

        return "ok";
    }

    @GetMapping("/education-list")
    public List<EducationVO> getEducation(Long memberId){
        return memberService.getEducationByMemberId(memberId);
    }

    @PostMapping("/create-education")
    public void createEducation(@RequestBody EducationVO educationVO){
        memberService.createEducation(educationVO);
    }

    @PatchMapping("/update-education")
    public String modifyEducation(@RequestBody EducationVO educationVO){
        educationVO.setStatus(true);
        memberService.modifyEducation(educationVO);
        return "ok";
    }

    @DeleteMapping("/delete-education")
    public String dropEducation(@RequestBody EducationVO educationVO){
        educationVO.setStatus(false);
        memberService.modifyEducation(educationVO);
        return "ok";
    }
}
