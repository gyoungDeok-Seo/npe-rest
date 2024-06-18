package com.app.nperest.repository;

import com.app.nperest.domain.*;
import com.app.nperest.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberDAO {
    private final MemberMapper memberMapper;
//    회원가입
    public void save(MemberVO memberVO){
        memberMapper.insert(memberVO);
    }
//    회원 정보 조회
    public Optional<MemberVO> findByKakaoEmail(String kakaoEmail){
        return memberMapper.selectByKakaoEmail(kakaoEmail);
    };
//    회원 정보 조회
    public Optional<MemberVO> findById(Long id){
        return memberMapper.selectById(id);
    };
//    회원 프로필 업데이트
    public void updateKakaoProfileUrl(MemberVO memberVO){
        memberMapper.updateKakaoProfileUrl(memberVO);
    };
//    회원 정보 수정
    public void updateMemberInfo(MemberVO memberVO){
        memberMapper.updateMemberInfo(memberVO);
    };
//    회원 기술 조회
    public List<MemberSkillDTO> findByKakaoEmailForMemberSkill(Long id){
        return memberMapper.selectMemberSkill(id);
    };
//    기술 검색
    public List<SkillVO> findByKeywordForSearchSkill(Search search){
        return memberMapper.selectSearchSkill(search);
    };
//    회원 기술 추가
    public void saveMemberSkill(MemberSkillDTO memberSkillDTO){
        memberMapper.insertMemberSkill(memberSkillDTO);
    };
//    회원 기술 삭제
    public void dropMemberSkill(MemberSkillDTO memberSkillDTO){
        memberMapper.deleteMemberSkill(memberSkillDTO);
    };
//    회원 질문 조회
    public List<MyQuestionDTO> findMyQuestions(Long memberId, Pagination pagination){
        return memberMapper.selectMyQuestions(memberId, pagination);
    };
//    회원 총 질문 수 조회
    public int countMyQuestions(Long memberId){
        return memberMapper.countMyQuestions(memberId);
    };
//    질문에 대한 총 답변 수 조회
    public int countAnswerCountForQuestion(Long questionId){
        return memberMapper.countAnswerCountForQuestion(questionId);
    };
//    답변에 대한 총 댓글 수 조회
    public int answerReplyCountForQuestion(Long memberId, Long questionId){
        return memberMapper.answerReplyCountForQuestion(memberId, questionId);
    };
//    회원 질문 조회
    public List<MyAnswerDTO> findMyAnswer(Long memberId, Pagination pagination){
        return memberMapper.selectMyAnswer(memberId, pagination);
    };
//    회원이 작성한 답글에 대한 좋아요 수 조회
    public int answerLikeCount(Long memberId, Long answerId){
        return memberMapper.answerLikeCount(memberId, answerId);
    };
//    회원이 작성한 답글에 대한 댓글 수 조회
    public int answerReplyCount(Long memberId, Long answerId){
        return memberMapper.answerReplyCount(memberId, answerId);
    };
//    회원이 작성한 답글의 전체 수 조회
    public int countMyAnswer(Long memberId){
        return memberMapper.countMyAnswer(memberId);
    };
//    답글에 대한 회원의 좋아요 조회
    public Optional<AnswerLikeVO> findAnswerLike(Long memberId, Long answerId){
        return memberMapper.selectAnswerLike(memberId, answerId);
    };
//    답글에 대한 회원의 좋아요 수정
    public void updateAnswerLike(AnswerLikeVO answerLikeVO){
        memberMapper.updateAnswerLike(answerLikeVO);
    };
//    답글에 대한 회원의 좋아요 생성
    public void saveAnswerLike(AnswerLikeVO answerLikeVO){
        memberMapper.insertAnswerLike(answerLikeVO);
    };
}
