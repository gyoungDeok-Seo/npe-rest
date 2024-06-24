package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
//    회원가입
    public void insert(MemberVO memberVO);
//    회원 정보 조회
    public Optional<MemberVO> selectByKakaoEmail(String kakaoEmail);
//    회원 정보 조회
    public Optional<MemberVO> selectById(Long id);
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO);
//    회원 정보 수정
    public void updateMemberInfo(MemberVO memberVO);
//    회원 기술 조회
    public List<MemberSkillDTO> selectMemberSkill(Long id);
//    기술 검색
    public List<SkillVO> selectSearchSkill(Search search);
//    회원 기술 추가
    public void insertMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 기술 삭제
    public void deleteMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 질문 조회
    public List<QuestionDTO> selectMyQuestions(Long memberId, Pagination pagination);
//    회원 총 질문 수 조회
    public int countMyQuestions(Long memberId);
//    질문에 대한 총 답변 수 조회
    public int countAnswerCountForQuestion(Long questionId);
//    답변에 대한 총 댓글 수 조회
    public int answerReplyCountForQuestion(Long memberId, Long questionId);
//    회원 질문 조회
    public List<MyAnswerDTO> selectMyAnswer(Long memberId, Pagination pagination);
//    회원이 작성한 답글에 대한 좋아요 수 조회
    public int answerLikeCount(Long answerId);
//    회원이 작성한 답글에 대한 댓글 수 조회
    public int answerReplyCount(Long memberId, Long answerId);
//    회원이 작성한 답글의 전체 수 조회
    public int countMyAnswer(Long memberId);
//    답글에 대한 회원의 좋아요 조회
    public Optional<AnswerLikeVO> selectAnswerLike(Long memberId, Long answerId);
//    답글에 대한 회원의 좋아요 수정
    public void updateAnswerLike(AnswerLikeVO answerLikeVO);
//    답글에 대한 회원의 좋아요 생성
    public void insertAnswerLike(AnswerLikeVO answerLikeVO);
//    회원 경력 생성
    public void insertCareer(CareerDTO careerDTO);
//    회원 경력 산업 분야 생성
    public void insertCareerIndustry(Long careerId, Long industryId);
//    회원 경력 스킬 생성
    public void insertCareerSkill(Long careerId, Long skillId);
//    회원 경력 조회
    public List<CareerDTO> selectCareer(Long memberId);
//    회원 경력별 산업 분야 조회
    public List<CareerIndustryDTO> selectCareerIndustry(Long careerId);
//    회원 경력별 스킬 조회
    public List<CareerSkillDTO> selectCareerSkill(Long careerId);
//    회원 경력 수정
    public void updateMemberCareer(CareerDTO careerDTO);
//    회원 경력별 산업 분야 삭제
    public void deleteCareerIndustries(CareerIndustryDTO careerIndustryDTO);
//    회원 경력별 스킬 삭제
    public void deleteCareerSkills(CareerSkillDTO careerSkillDTO);
//    회원 교육 수정
    public void insertEducation(EducationVO educationVO);
//    회원 교육 전체 조회
    public List<EducationVO> selectEducationByMemberId(Long memberId);
//    회원 교육 수정 및 삭제
    public void updateEducation(EducationVO educationVO);
}
