package com.github.aborn.mindpress.service.impl;

import com.github.aborn.mindpress.domain.Content;
import com.github.aborn.mindpress.inf.exception.EntityExistException;
import com.github.aborn.mindpress.inf.utils.PageUtil;
import com.github.aborn.mindpress.inf.utils.ValidationUtil;
import com.github.aborn.mindpress.repository.ContentRepository;
import com.github.aborn.mindpress.service.ContentService;
import com.github.aborn.mindpress.service.dto.ContentDto;
import com.github.aborn.mindpress.service.dto.ContentQueryCriteria;
import com.github.aborn.mindpress.service.mapstruct.ContentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author aborn
 * @description 服务实现
 * @date 2022-05-28
 **/
@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Override
    public Map<String, Object> queryAll(ContentQueryCriteria criteria, Pageable pageable) {
        Page<Content> page = contentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(contentMapper::toDto));
    }

    @Override
    public List<ContentDto> queryAll(ContentQueryCriteria criteria) {
        return contentMapper.toDto(contentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    @Transactional
    public ContentDto findById(Long id) {
        Content content = contentRepository.findById(id).orElseGet(Content::new);
        ValidationUtil.isNull(content.getId(), "Content", "id", id);
        return contentMapper.toDto(content);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ContentDto create(Content resources) {
        if (contentRepository.findByArticleid(resources.getArticleid()) != null) {
            throw new EntityExistException(Content.class, "articleid", resources.getArticleid());
        }
        return contentMapper.toDto(contentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Content resources) {
        Content content = contentRepository.findById(resources.getId()).orElseGet(Content::new);
        ValidationUtil.isNull(content.getId(), "Content", "id", resources.getId());
        Content content1 = null;
        content1 = contentRepository.findByArticleid(resources.getArticleid());
        if (content1 != null && !content1.getId().equals(content.getId())) {
            throw new EntityExistException(Content.class, "articleid", resources.getArticleid());
        }
        content.copy(resources);
        contentRepository.save(content);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            contentRepository.deleteById(id);
        }
    }
}