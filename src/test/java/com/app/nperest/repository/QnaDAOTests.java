package com.app.nperest.repository;

import com.app.nperest.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class QnaDAOTests {

    @Autowired
    private QnaDAO QnaDAO;


    @Test
    public void selectCategoryListTest() {
        System.out.println(QnaDAO.selectCategoryList());
    }

    @Test
    public void selectTopTenTest() {
        System.out.println(QnaDAO.selectTopTen());
    }
}
