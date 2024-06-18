package com.app.nperest.service;

import com.app.nperest.domain.*;

import java.util.List;
import java.util.Optional;

public interface MemberService {
//    회원가입
    public void join(MemberVO memberVO);
//    회원 정보 조회
    public Optional<MemberVO> getMemberByKakaoEmail(String kakaoEmail);
//    회원 정보 조회
    public Optional<MemberVO> getMemberById(Long id);
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO);
//    회원 정보 수정
    public void modifyMemberInfo(MemberVO memberVO);
//    회원 기술 조회
    public List<MemberSkillDTO> getMemberSkill(Long id);
//    기술 검색
    public List<SkillVO> getSearchSkillList(Search search);
//    회원 기술 추가
    public void saveMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 기술 삭제
    public void dropMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 질문 조회
    public List<MyQuestionDTO> getMyQuestions(Long memberId, Pagination pagination);
//    회원 총 질문 수 조회
    public int countMyQuestions(Long memberId);
//    질문에 대한 총 답변 수 조회
    public int countAnswerCountForQuestion(Long questionId);
//    답변에 대한 총 댓글 수 조회
    public int answerReplyCountForQuestion(Long memberId, Long questionId);
//    회원 질문 조회
    public List<MyAnswerDTO> getMyAnswers(Long memberId, Pagination pagination);
//    회원이 작성한 답글에 대한 좋아요 수 조회
    public int getAnswerLikeCount(Long memberId, Long answerId);
//    회원이 작성한 답글에 대한 댓글 수 조회
    public int getAnswerReplyCount(Long memberId, Long answerId);
//    회원이 작성한 답글의 전체 수 조회
    public int getCountMyAnswer(Long memberId);
//    답글에 대한 회원의 좋아요 조회
    public Optional<AnswerLikeVO> getAnswerLike(Long memberId, Long answerId);
//    답글에 대한 회원의 좋아요 수정
    public void modifyAnswerLike(AnswerLikeVO answerLikeVO);
//    답글에 대한 회원의 좋아요 생성
    public void creatAnswerLike(AnswerLikeVO answerLikeVO);
}
