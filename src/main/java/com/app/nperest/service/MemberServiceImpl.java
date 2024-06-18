package com.app.nperest.service;

import com.app.nperest.domain.*;
import com.app.nperest.repository.MemberDAO;
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
//    회원가입
    @Override
    public void join(MemberVO memberVO) {
//        유무 검사를 위해 Optional 객체로 생성
        Optional<MemberVO> foundMember = getMember(memberVO.getKakaoEmail());
//        1. 최초 로그인 검사
        if (foundMember.isEmpty()){
            memberVO.setMemberPosition(" ");
            memberDAO.save(memberVO);
        } else { // 이메일 정보가 있을 경우
            MemberVO member = foundMember.get();
            String oldUrl = member.getKakaoProfileUrl();
            String newUrl = memberVO.getKakaoProfileUrl();
            if (!Objects.equals(oldUrl, newUrl)) { // 기존 프로필과 다를 경우
                member.setKakaoProfileUrl(newUrl);
                updateKakaoProfileUrl(member);
            }
        }
    }
//    회원 정보 조회
    @Override
    public Optional<MemberVO> getMember(String kakaoEmail){
        return memberDAO.findByKakaoEmail(kakaoEmail);
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
    public List<MemberSkillDTO> getMemberSkill(String kakaoEmail){
        return memberDAO.findByKakaoEmailForMemberSkill(kakaoEmail);
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
    public List<MyQuestionDTO> getMyQuestions(Long memberId, Pagination pagination){
        pagination.setTotal(countMyQuestions(memberId));
        pagination.progress();

        List<MyQuestionDTO> myQuestionList = memberDAO.findMyQuestions(memberId, pagination);

        for (MyQuestionDTO myQuestion : myQuestionList) {
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
        pagination.progress();

        List<MyAnswerDTO> myAnswerList = memberDAO.findMyAnswer(memberId, pagination);

        for (MyAnswerDTO myAnswer : myAnswerList){
            myAnswer.setAnswerLikeCount(getAnswerLikeCount(memberId, myAnswer.getId()));
            myAnswer.setAnswerReplyCount(getAnswerReplyCount(memberId, myAnswer.getId()));
            myAnswer.setMyAnswerTotalCount(getCountMyAnswer(memberId));
        }

        return myAnswerList;
    };
//    회원이 작성한 답글에 대한 좋아요 수 조회
    @Override
    public int getAnswerLikeCount(Long memberId, Long answerId){
        return memberDAO.answerLikeCount(memberId, answerId);
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
    };
}
