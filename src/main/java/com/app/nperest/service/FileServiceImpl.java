package com.app.nperest.service;

import com.app.nperest.domain.FileVO;
import com.app.nperest.repository.FileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService{
    private final FileDAO fileDAO;

    @Override
    public List<FileVO> selectFileList(Long id) {
        return fileDAO.selectFileList(id);
    }
}
