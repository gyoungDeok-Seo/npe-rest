package com.app.nperest.mapper;

import com.app.nperest.domain.TagVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagMapper {
     public List<TagVO> selectTagList(Long id);
}
