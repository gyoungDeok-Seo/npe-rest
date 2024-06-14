package com.app.nperest.mapper;

import com.app.nperest.domain.FileDTO;
import com.app.nperest.domain.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaMapper {
    int insertQna(QnaDTO qnaDTO);
    int insertFile(FileDTO fileDTO);
}
