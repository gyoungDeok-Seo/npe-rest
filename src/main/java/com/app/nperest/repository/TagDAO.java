package com.app.nperest.repository;

import com.app.nperest.domain.TagVO;
import com.app.nperest.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagDAO {
    private final TagMapper tagMapper;

    public List<TagVO> selectTagList(Long id) {
        return tagMapper.selectTagList(id);
    }
}
