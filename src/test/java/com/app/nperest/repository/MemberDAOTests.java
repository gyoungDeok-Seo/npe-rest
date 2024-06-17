package com.app.nperest.repository;

import com.app.nperest.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class MemberDAOTests {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void save(){
        Optional<MemberVO> foundMember = memberDAO.findByKakaoEmail("dlfjs158@nate.com");
        log.info("member: {}", foundMember.get());
    }

    @Test
    public void findByKakaoEmailTest(){
        Optional<MemberVO> foundMember = memberDAO.findByKakaoEmail("dlfjs158@nate.com");
        log.info("member: {}", foundMember.get());
    }
}
