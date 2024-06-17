package com.app.nperest.mapper;

import com.app.nperest.domain.MemberSkillDTO;
import com.app.nperest.domain.MemberVO;
import com.app.nperest.domain.Search;
import com.app.nperest.domain.SkillVO;
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
}
