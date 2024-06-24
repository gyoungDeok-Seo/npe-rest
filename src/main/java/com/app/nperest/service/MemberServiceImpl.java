package com.app.nperest.service;

import com.app.nperest.domain.*;
import com.app.nperest.repository.MemberDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Primary
public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;

    private final HttpSession session;
//    회원가입
    @Override
    public void join(MemberVO memberVO) {
        long result = 0L;
//        유무 검사를 위해 Optional 객체로 생성
        Optional<MemberVO> foundMember = getMemberByKakaoEmail(memberVO.getKakaoEmail());
//        1. 최초 로그인 검사
        if (foundMember.isEmpty()){
            memberVO.setMemberPosition(" ");
            memberDAO.save(memberVO);
            result = memberVO.getId();
        } else { // 이메일 정보가 있을 경우
            MemberVO member = foundMember.get();
            String oldUrl = member.getKakaoProfileUrl();
            String newUrl = memberVO.getKakaoProfileUrl();
            if (!Objects.equals(oldUrl, newUrl)) { // 기존 프로필과 다를 경우
                member.setKakaoProfileUrl(newUrl);
                updateKakaoProfileUrl(member);
            }
            result = member.getId();
        }
        MemberVO sessionMember = getMemberById(result).get();

        session.setAttribute("member", sessionMember);
    }
//    회원 정보 조회
    @Override
    public Optional<MemberVO> getMemberByKakaoEmail(String kakaoEmail){
        return memberDAO.findByKakaoEmail(kakaoEmail);
    }
//    회원 정보 조회
    @Override
    public Optional<MemberVO> getMemberById(Long id){
        MemberVO member = (MemberVO) session.getAttribute("member");
        if(member == null && id == null){
            return Optional.empty();
        }
        if(id == null){
            return memberDAO.findById(member.getId());
        }
        return memberDAO.findById(id);
    }
//    회원 프로필 업데이트
    @Override
    public void updateKakaoProfileUrl(MemberVO memberVO){
        memberDAO.updateKakaoProfileUrl(memberVO);
    };
//    회원 정보 수정
    public void modifyMemberInfo(MemberVO memberVO){
        memberDAO.updateMemberInfo(memberVO);
    };
//    회원 기술 조회
    @Override
    public List<MemberSkillDTO> getMemberSkill(Long id){
        return memberDAO.findByKakaoEmailForMemberSkill(id);
    };
//    기술 검색
    @Override
    public List<SkillVO> getSearchSkillList(Search search){
        return memberDAO.findByKeywordForSearchSkill(search);
    };
//    회원 기술 추가
    @Override
    public void saveMemberSkill(MemberSkillDTO memberSkillDTO){
        memberDAO.saveMemberSkill(memberSkillDTO);
    };
//    회원 기술 삭제
    @Override
    public void dropMemberSkill(MemberSkillDTO memberSkillDTO){
        memberDAO.dropMemberSkill(memberSkillDTO);
    };
//    회원 질문 조회
    @Override
    public List<QuestionDTO> getMyQuestions(Long memberId, Pagination pagination){
        pagination.setTotal(countMyQuestions(memberId));
        pagination.progress(20);

        List<QuestionDTO> myQuestionList = memberDAO.findMyQuestions(memberId, pagination);

        for (QuestionDTO myQuestion : myQuestionList) {
            myQuestion.setAnswerCount(countAnswerCountForQuestion(myQuestion.getId()));
            myQuestion.setAnswerReplyCount(answerReplyCountForQuestion(memberId, myQuestion.getId()));
            myQuestion.setMyQuestionTotalCount(countMyQuestions(memberId));
            myQuestion.setPagination(pagination);
        };

        return myQuestionList;
    };
//    회원 총 질문 수 조회
    @Override
    public int countMyQuestions(Long memberId){
        return memberDAO.countMyQuestions(memberId);
    };
//    질문에 대한 총 답변 수 조회
    @Override
    public int countAnswerCountForQuestion(Long questionId){
        return memberDAO.countAnswerCountForQuestion(questionId);
    };
//    답변에 대한 총 댓글 수 조회
    @Override
    public int answerReplyCountForQuestion(Long memberId, Long questionId){
        return memberDAO.answerReplyCountForQuestion(memberId, questionId);
    };
//    회원 답글 조회
    @Override
    public List<MyAnswerDTO> getMyAnswers(Long memberId, Pagination pagination){
        pagination.setTotal(getCountMyAnswer(memberId));
        pagination.progress(10);

        List<MyAnswerDTO> myAnswerList = memberDAO.findMyAnswer(memberId, pagination);

        for (MyAnswerDTO myAnswer : myAnswerList){
            myAnswer.setAnswerLikeCount(getAnswerLikeCount(myAnswer.getId()));
            myAnswer.setAnswerReplyCount(getAnswerReplyCount(memberId, myAnswer.getId()));
            myAnswer.setMyAnswerTotalCount(getCountMyAnswer(memberId));
        }

        return myAnswerList;
    };
//    회원이 작성한 답글에 대한 좋아요 수 조회
    @Override
    public int getAnswerLikeCount(Long answerId){
        return memberDAO.answerLikeCount(answerId);
    };
//    회원이 작성한 답글에 대한 댓글 수 조회
    @Override
    public int getAnswerReplyCount(Long memberId, Long answerId){
        return memberDAO.answerReplyCount(memberId, answerId);
    };
//    회원이 작성한 답글의 전체 수 조회
    @Override
    public int getCountMyAnswer(Long memberId){
        return memberDAO.countMyAnswer(memberId);
    };
//    답글에 대한 회원의 좋아요 조회
    @Override
    public Optional<AnswerLikeVO> getAnswerLike(Long memberId, Long answerId){
        return memberDAO.findAnswerLike(memberId, answerId);
    }
//    답글에 대한 회원의 좋아요 수정
    @Override
    public void modifyAnswerLike(AnswerLikeVO answerLikeVO){
        memberDAO.updateAnswerLike(answerLikeVO);
    };
//    답글에 대한 회원의 좋아요 생성
    public void creatAnswerLike(AnswerLikeVO answerLikeVO){
        memberDAO.saveAnswerLike(answerLikeVO);
    }

    @Override
    public void createCareer(CareerDTO careerDTO) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        careerDTO.setMemberId(member.getId());

        memberDAO.saveCareer(careerDTO);

        if(careerDTO.getCareerIndustries() != null){
            for(CareerIndustryDTO careerIndustryDTO : careerDTO.getCareerIndustries()) {
                createCareerIndustry(careerDTO.getId(), careerIndustryDTO.getIndustryId());
            };
        }

        if(careerDTO.getCareerSkills() != null){
            for(CareerSkillDTO careerSkillDTO : careerDTO.getCareerSkills()) {
                createCareerSkill(careerDTO.getId(), careerSkillDTO.getSkillId());
            }
        }
    }

    @Override
    public void createCareerIndustry(Long careerId, Long industryId) {
        memberDAO.saveCareerIndustry(careerId, industryId);
    }

    @Override
    public void createCareerSkill(Long careerId, Long skillId) {
        memberDAO.saveCareerSkill(careerId, skillId);
    }

    @Override
    public List<CareerDTO> getCareerByMemberId(Long memberId) {
        List<CareerDTO> result = memberDAO.findCareerByMemberId(memberId);

        if(result != null) {
            for(CareerDTO careerDTO : result){
                careerDTO.setCareerIndustries(getCareerIndustryByCareerId(careerDTO.getId()));
                careerDTO.setCareerSkills(getCareerSkillByCareerId(careerDTO.getId()));
            }
        }

        return result;
    }

    @Override
    public List<CareerIndustryDTO> getCareerIndustryByCareerId(Long careerId) {
        return memberDAO.findCareerIndustryByCareerId(careerId);
    }

    @Override
    public List<CareerSkillDTO> getCareerSkillByCareerId(Long careerId) {
        return memberDAO.findCareerSkillByCareerId(careerId);
    }

    @Override
    public void modifyMemberCareer(CareerDTO careerDTO) {
        memberDAO.updateMemberCareer(careerDTO);
    }

    @Override
    public void dropCareerIndustries(CareerIndustryDTO careerIndustryDTO) {
        memberDAO.dropCareerIndustries(careerIndustryDTO);
    }

    @Override
    public void dropCareerSkills(CareerSkillDTO careerSkillDTO) {
        memberDAO.dropCareerSkills(careerSkillDTO);
    }

    @Override
    public void createEducation(EducationVO educationVO) {
        MemberVO member = (MemberVO) session.getAttribute("member");
        educationVO.setMemberId(member.getId());
        memberDAO.saveEducation(educationVO);
    }

    @Override
    public List<EducationVO> getEducationByMemberId(Long memberId) {
        return memberDAO.findEducationByMemberId(memberId);

    }

    @Override
    public void modifyEducation(EducationVO educationVO) {
        memberDAO.updateEducation(educationVO);
    }
}
