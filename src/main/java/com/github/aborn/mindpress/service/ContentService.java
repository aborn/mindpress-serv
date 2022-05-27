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
package com.github.aborn.mindpress.service;

import com.github.aborn.mindpress.domain.Content;
import com.github.aborn.mindpress.service.dto.ContentDto;
import com.github.aborn.mindpress.service.dto.ContentQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aborn
 * @website https://sancaiwulian.com
 * @description 服务接口
 * @date 2022-05-28
 **/
public interface ContentService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    Map<String, Object> queryAll(ContentQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<ContentDto>
    */
    List<ContentDto> queryAll(ContentQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return ContentDto
     */
    ContentDto findById(Long id);

    /**
    * 创建
    * @param resources /
    * @return ContentDto
    */
    ContentDto create(Content resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(Content resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(Long[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<ContentDto> all, HttpServletResponse response) throws IOException;
}