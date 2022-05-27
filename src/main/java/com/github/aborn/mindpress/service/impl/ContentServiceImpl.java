/*
 *  Copyright 2022-2022 Sancaiwulian Holding Ltd (苏州三才物联网科技有限公司).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.aborn.mindpress.service.impl;

import com.github.aborn.mindpress.domain.Content;
import com.sancaiwulian.smes.inf.exception.EntityExistException;
import com.sancaiwulian.smes.inf.utils.ValidationUtil;
import com.sancaiwulian.smes.inf.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.github.aborn.mindpress.repository.ContentRepository;
import com.github.aborn.mindpress.service.ContentService;
import com.github.aborn.mindpress.service.dto.ContentDto;
import com.github.aborn.mindpress.service.dto.ContentQueryCriteria;
import com.github.aborn.mindpress.service.mapstruct.ContentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.sancaiwulian.smes.inf.utils.PageUtil;
import com.sancaiwulian.smes.inf.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author aborn
 * @website https://sancaiwulian.com
 * @description 服务实现
 * @date 2022-05-28
 **/
@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;

    @Override
    public Map<String,Object> queryAll(ContentQueryCriteria criteria, Pageable pageable){
        Page<Content> page = contentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(contentMapper::toDto));
    }

    @Override
    public List<ContentDto> queryAll(ContentQueryCriteria criteria){
        return contentMapper.toDto(contentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public ContentDto findById(Long id) {
        Content content = contentRepository.findById(id).orElseGet(Content::new);
        ValidationUtil.isNull(content.getId(),"Content","id",id);
        return contentMapper.toDto(content);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ContentDto create(Content resources) {
        if(contentRepository.findByArticleid(resources.getArticleid()) != null){
            throw new EntityExistException(Content.class,"articleid",resources.getArticleid());
        }
        return contentMapper.toDto(contentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Content resources) {
        Content content = contentRepository.findById(resources.getId()).orElseGet(Content::new);
        ValidationUtil.isNull( content.getId(),"Content","id",resources.getId());
        Content content1 = null;
        content1 = contentRepository.findByArticleid(resources.getArticleid());
        if(content1 != null && !content1.getId().equals(content.getId())){
            throw new EntityExistException(Content.class,"articleid",resources.getArticleid());
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

    @Override
    public void download(List<ContentDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ContentDto content : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("文章唯一标识", content.getArticleid());
            map.put("文章标题", content.getTitle());
            map.put("文章描述", content.getDesc());
            map.put("Markdown文本内容", content.getContent());
            map.put("创建者，文章作者", content.getCreateBy());
            map.put("更新者", content.getUpdateBy());
            map.put("创建日期", content.getCreateTime());
            map.put("更新时间", content.getUpdateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}