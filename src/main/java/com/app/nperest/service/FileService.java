package com.app.nperest.service;

import com.app.nperest.domain.FileVO;

import java.util.List;

public interface FileService {
    public List<FileVO> selectList(FileVO fileVO);
}
