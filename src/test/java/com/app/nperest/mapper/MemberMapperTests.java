package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberMapperTests {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insertTest(){
        MemberVO memberVO = new MemberVO();
        memberVO.setKakaoProfileUrl("sadf");
        memberVO.setKakaoEmail("hong1234@gmail.com");
        memberVO.setMemberName("홍길동");
        memberVO.setMemberPosition("직원");
        memberVO.setMemberIntro("잘 부탁드립니다.");

        memberMapper.insert(memberVO);
    }

    @Test
    public void selectTest() {
        String kakaoEmail = "hong1234@gmail.com";
        Optional<MemberVO> member = memberMapper.select(kakaoEmail);

        log.info("member: {}", member);
    }

    @Test
    public void updateKakaoProfileUrlTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(1L);
        memberVO.setKakaoProfileUrl("변경된 url");
        memberMapper.updateKakaoProfileUrl(memberVO);
    }

    @Test
    public void selectMemberSkillTest(){
        List<MemberSkillDTO> memberSkills = memberMapper.selectMemberSkill("dlfjs158@nate.com");
        if(memberSkills.isEmpty()){
            log.info("비었다");
        } else {
            log.info("MemberSkillDTO: {}", memberSkills);
        }
    }

    @Test
    public void selectSearchSkillTest(){
        Search search = new Search();
        search.setKeyword("j");

        List<SkillVO> searchSkills = memberMapper.selectSearchSkill(search);
        if(searchSkills.isEmpty()){
            log.info("비었다");
        } else {
            log.info("SkillVO: {}", searchSkills);
        }
    }

    @Test
    public void selectMyQuestionsTest(){
        Pagination pagination = new Pagination();
        pagination.setPage(1);
        pagination.setTotal(memberMapper.countMyQuestions(2L));
        pagination.progress();

        List<MyQuestionDTO> myQuestionDTOs = memberMapper.selectMyQuestions(2L, pagination);
        log.info("MyQuestionDTO: {}", myQuestionDTOs);
    }

    @Test
    public void countMyQuestionsTest() {
//        countAnswerCountForQuestion
//        answerReplyCountForQuestion
        Long memberId = 2L;
        log.info("countMyQuestions: {}", memberMapper.countMyQuestions(memberId));
    }
}
