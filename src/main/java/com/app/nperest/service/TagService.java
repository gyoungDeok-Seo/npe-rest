package com.app.nperest.service;

import com.app.nperest.domain.TagVO;

import java.util.List;

public interface TagService {
    public List<TagVO> selectTagList(Long id);
}
