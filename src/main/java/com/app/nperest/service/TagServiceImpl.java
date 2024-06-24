package com.app.nperest.service;

import com.app.nperest.domain.TagVO;
import com.app.nperest.repository.TagDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TagServiceImpl implements TagService {
    private final TagDAO tagDAO;

    @Override
    public List<TagVO> selectTagList(Long id) {
        return tagDAO.selectTagList(id);
    }

}
