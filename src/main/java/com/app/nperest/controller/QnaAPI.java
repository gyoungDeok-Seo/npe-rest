package com.app.nperest.controller;

import com.app.nperest.domain.*;
import com.app.nperest.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //        결과를 isOk 변수에 저장,
//        insert 메소드는 성공 시 양수를 반환하고, 실패 시 0이나 음수를 반환
        qnaService.insert(qnaDTO);
//        저장할 빈 HashMap 객체를 생성
//        자바스크립트의 객체 느낌
        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        return response;
    }

    // answerCnt랑 returnTag 리턴 확인 필요
    @GetMapping("/list")
    public Map<String, Object> selectList(HttpSession session,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(name = "startPage", defaultValue = "0") int startPage,
                                          @RequestParam(name = "tag", defaultValue = "") String tag) {
        QnaDTO qnaDTO = new QnaDTO();
        qnaDTO.setPageSize(pageSize);
        qnaDTO.setStartPage(startPage);

        // 태그 필터링을 위해 태그 정보를 설정
        if (!tag.isEmpty()) {
            TagVO tagVO = new TagVO();
            tagVO.setTagName(tag);
            qnaDTO.setTags(Collections.singletonList(tagVO));
        }

        List<QnaDTO> list = qnaService.selectList(qnaDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("successMsg", true);
        response.put("list", list);

        return response;
    }

    @GetMapping("/detail/{id}")
    public Map<String, Object> detailQnas(HttpSession session, @PathVariable("id") Long id) {
        MemberVO memberVO = (MemberVO) session.getAttribute("member");
        QnaDetailDTO qnaDetailDTO = new QnaDetailDTO();
        qnaDetailDTO.setMemberId(memberVO.getId());
        qnaDetailDTO.setId(id);
        QnaDetailDTO detail = qnaService.selectDetail(qnaDetailDTO);

        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setQuestionId(id);
        List<AnswerDTO> answerList = answerService.selectList(answerDTO);

        for (AnswerDTO answer : answerList) {
            AnswerReplyDTO replyDTO = new AnswerReplyDTO();
            replyDTO.setAnswerId(answer.getId());
            replyDTO.setMemberId(memberVO.getId()); // 현재 회원의 ID를 설정합니다.
            List<AnswerReplyDTO> replyList = answerReplyService.selectList(replyDTO);
            answer.setReplyList(replyList);
        }

        TagVO tagVO = new TagVO();
        tagVO.setQuestionId(id);
        List<TagVO> tagList = tagService.selectList(tagVO);

        FileVO fileVO = new FileVO();
        fileVO.setQuestionId(id);
        List<FileVO> fileList = fileService.selectList(fileVO);

        Map<String, Object> response = new HashMap<>();

        response.put("id", detail.getId());
        response.put("questionTitle", detail.getQuestionTitle());
        response.put("questionContent", detail.getQuestionContent());
        response.put("status", detail.isStatus());
        response.put("categoryName", detail.getCategoryName());
        response.put("categoryValue", detail.getCategoryValue());
        response.put("memberId", detail.getMemberId());
        response.put("createdDate", detail.getCreatedDate());
        response.put("updatedDate", detail.getUpdatedDate());
        response.put("answerList", answerList);
        response.put("fileList", fileList);
        response.put("tagList", tagList);
        return response;
    }

}
