package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;
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
        Optional<MemberVO> member = memberMapper.selectByKakaoEmail(kakaoEmail);

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
        List<MemberSkillDTO> memberSkills = memberMapper.selectMemberSkill(2L);
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
        pagination.progress(20);

        List<QuestionDTO> questionDTOS = memberMapper.selectMyQuestions(2L, pagination);
        log.info("MyQuestionDTO: {}", questionDTOS);
    }

    @Test
    public void countMyQuestionsTest() {
//        countAnswerCountForQuestion
//        answerReplyCountForQuestion
        Long memberId = 2L;
        log.info("countMyQuestions: {}", memberMapper.countMyQuestions(memberId));
    }

    @Test
    public void answerLikeCountTest() {
        Long memberId = 2L;
        Long answerId = 74L;

        log.info("answerLikeCount: {}", memberMapper.answerLikeCount(answerId));
    }
    @Test
    public void insertCareerTest() {
        CareerDTO careerDTO = new CareerDTO();
        careerDTO.setCompanyName("더미데이터3컴퍼니");
        careerDTO.setMemberPosition("개발팀 팀장");
        careerDTO.setCareerStart("2020-06-01");
        careerDTO.setCareerEnd("9999-99-99");
        careerDTO.setDescription("대충 소개");
        careerDTO.setCareerUrl("www.naver.com");
        careerDTO.setMemberId(2L);

        if(Objects.equals(careerDTO.getCareerEnd(), "9999-99-99")){
            careerDTO.setCareerEnd(null);
        }

        memberMapper.insertCareer(careerDTO);
    }
}
