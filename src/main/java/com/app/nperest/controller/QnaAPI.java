package com.app.nperest.controller;

import com.app.nperest.domain.*;
import com.app.nperest.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//@RestController:
//restful 컨트롤러임을 나타낸다. 이 클래스의 메서드들은 json으로 데이터를  반환
//@RequiredArgsConstructor
//lombok 라이브러리 어노테이션, final로 선언된 모든 필드의 생성자를 자동으로 생성
@RestController
@RequestMapping("/qnas/api")
@RequiredArgsConstructor
public class QnaAPI {
    private final QnaService qnaService;
    private final AnswerService answerService;
    private final AnswerReplyService answerReplyService;
    private final TagService tagService;
    private final FileService fileService;

    @PostMapping("/create")
    public Map<String, Object> createQna(HttpSession session, @RequestBody QnaDTO qnaDTO) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        qnaDTO.setMemberId(memberVO.getId());

        for (FileVO file : qnaDTO.getFiles()) {
            file.setFilePath(getPath());
        }
//        결과를 isOk 변수에 저장,
//        insert 메소드는 성공 시 양수를 반환하고, 실패 시 0이나 음수를 반환
        qnaService.insert(qnaDTO);
//        저장할 빈 HashMap 객체를 생성
//        자바스크립트의 객체 느낌

        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    @GetMapping("/list")
    public List<QnaDTO> selectQnaList(String tags, String category, Pagination pagination) {
        List<String> tagList = Arrays.asList(tags.split(","));
        List<QnaDTO> list = qnaService.selectQnaList(tagList, category, pagination);
        int replyCnt = 0;
        for (QnaDTO qna : list) {
            if (qna.getAnswerList() != null) {
                for (AnswerVO answer : qna.getAnswerList()) {
                    replyCnt += answerReplyService.selectReplyCount(answer.getId());
                }
                qna.setReplyCnt(replyCnt);
            }
            qna.setTags(tagService.selectTagList(qna.getId()));
        }
        return list;
    }

    @GetMapping("/delete")
    public Map<String, Object> createQna(HttpSession session, Long id) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        QnaDetailDTO qnaDetailDTO = qnaService.selectQnaDetail(id);
        if (Objects.equals(qnaDetailDTO.getMemberId(), memberVO.getId())) {
            qnaService.delete(id);
        }
        return null;
    }


    @GetMapping("/detail")
    public QnaDetailDTO selectQnaDetail(HttpSession session, Long id) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        qnaService.incrementHits(id);
        QnaDetailDTO qnaDetail = qnaService.selectQnaDetail(id);
        qnaDetail.setId(id);
        qnaDetail.setMaster(Objects.equals(qnaDetail.getMemberId(), memberVO.getId()));

        List<TagVO> tagList = tagService.selectTagList(id);
        List<FileVO> fileList = fileService.selectFileList(id);
        qnaDetail.setTags(tagList);
        qnaDetail.setFiles(fileList);

        return qnaDetail;
    }

    // 메인화면에 카테고리 리스트
    @GetMapping("category/list")
    public List<CategoryVO> selectCategoryList() {
        return qnaService.selectCategoryList();
    }

    @GetMapping("top-ten/list")
    public List<QnaDTO> selectTopTen() {
        return qnaService.selectTopTen();
    }

    @GetMapping("best-answer/list")
    public List<QnaDTO> selectBestAnswer() {
        return qnaService.selectBestAnswer();
    }

    @GetMapping("/list-tag")
    public Map<String, Object> selectTagQnaList(String tag, Pagination pagination) {
        List<QnaDTO> list = qnaService.selectTagQnaList(tag, pagination);
        for (QnaDTO qna : list) {
            qna.setTags(tagService.selectTagList(qna.getId()));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("list", qnaService.selectTagQnaList(tag, pagination));
        data.put("listCount", qnaService.tagQnaListCount(tag));
        return data;
    }

    private String getPath() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }
}
