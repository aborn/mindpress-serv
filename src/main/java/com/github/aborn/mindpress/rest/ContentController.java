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
package com.github.aborn.mindpress.rest;

import com.sancaiwulian.smes.inf.annotation.Log;
import com.github.aborn.mindpress.domain.Content;
import com.github.aborn.mindpress.service.ContentService;
import com.github.aborn.mindpress.service.dto.ContentQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aborn
 * @website https://sancaiwulian.com
 * @date 2022-05-28
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "Markdown内容管理")
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('content:list')")
    public void exportContent(HttpServletResponse response, ContentQueryCriteria criteria) throws IOException {
        contentService.download(contentService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询Markdown内容")
    @ApiOperation("查询Markdown内容")
    @PreAuthorize("@el.check('content:list')")
    public ResponseEntity<Object> queryContent(ContentQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(contentService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增Markdown内容")
    @ApiOperation("新增Markdown内容")
    @PreAuthorize("@el.check('content:add')")
    public ResponseEntity<Object> createContent(@Validated @RequestBody Content resources) {
        return new ResponseEntity<>(contentService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改Markdown内容")
    @ApiOperation("修改Markdown内容")
    @PreAuthorize("@el.check('content:edit')")
    public ResponseEntity<Object> updateContent(@Validated @RequestBody Content resources) {
        contentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除Markdown内容")
    @ApiOperation("删除Markdown内容")
    @PreAuthorize("@el.check('content:del')")
    public ResponseEntity<Object> deleteContent(@RequestBody Long[] ids) {
        contentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}