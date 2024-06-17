package com.app.nperest.mapper;

import com.app.nperest.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaMapper {
    public void insertQna(QnaDTO qnaDTO);

    public void insertFile(FileVO fileVO);

    public void insertTag(TagVO tagVO);

    public List<QnaDTO> selectList(QnaDTO qnaDTO);

    public QnaDetailDTO selectDetail(QnaDetailDTO qnaDetailDTO);
}
