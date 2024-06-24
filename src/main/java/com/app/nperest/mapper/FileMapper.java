package com.app.nperest.mapper;

import com.app.nperest.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
      public List<FileVO> selectFileList(Long id);
}
