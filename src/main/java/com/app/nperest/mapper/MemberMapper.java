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
    public Optional<MemberVO> select(String kakaoEmail);
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO);
//    회원 정보 수정
    public void updateMemberInfo(MemberVO memberVO);
//    회원 기술 조회
    public List<MemberSkillDTO> selectMemberSkill(String kakaoEmail);
//    기술 검색
    public List<SkillVO> selectSearchSkill(Search search);
//    회원 기술 추가
    public void insertMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 기술 삭제
    public void deleteMemberSkill(MemberSkillDTO memberSkillDTO);
//    회원 질문 조회
    public List<MyQuestionDTO> selectMyQuestions(Long memberId, Pagination pagination);
//    회원 총 질문 수 조회
    public int countMyQuestions(Long memberId);
//    질문에 대한 총 답변 수 조회
    public int countAnswerCountForQuestion(Long questionId);
//    답변에 대한 총 댓글 수 조회
    public int answerReplyCountForQuestion(Long memberId, Long questionId);
//    회원 질문 조회
    public List<MyAnswerDTO> selectMyAnswer(Long memberId, Pagination pagination);
//    회원이 작성한 답글에 대한 좋아요 수 조회
    public int answerLikeCount(Long memberId, Long answerId);
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
}
