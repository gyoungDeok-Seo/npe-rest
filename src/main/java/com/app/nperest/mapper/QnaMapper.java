package com.app.nperest.mapper;

import com.app.nperest.domain.FileVO;
import com.app.nperest.domain.QnaDTO;
import com.app.nperest.domain.TagVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {
    public void insertQna(QnaDTO qnaDTO);

    public void insertFile(FileVO fileVO);

    public void insertTag(TagVO tagVO);
}
