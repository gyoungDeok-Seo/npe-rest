package com.app.nperest.repository;

import com.app.nperest.domain.FileVO;
import com.app.nperest.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

    public List<FileVO> selectFileList(Long id) {
        return fileMapper.selectFileList(id);
    }
}
