package com.github.aborn.mindpress.rest;

import com.github.aborn.mindpress.domain.MarkdownMeta;
import com.github.aborn.mindpress.service.MarkdownMetaService;
import com.github.aborn.mindpress.service.dto.MarkdownMetaQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 * @author aborn
 * @date 2022-05-29
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "Markdown meta infos管理")
@RequestMapping("/api/markdownMeta")
public class MarkdownMetaController {

    private final MarkdownMetaService markdownMetaService;

    @GetMapping
    @ApiOperation("query Markdown meta infos")
    public ResponseEntity<Object> queryMarkdownMeta(MarkdownMetaQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(markdownMetaService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation("create Markdown meta infos")
    public ResponseEntity<Object> createMarkdownMeta(@Validated @RequestBody MarkdownMeta resources) {
        return new ResponseEntity<>(markdownMetaService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("update Markdown meta infos")
    public ResponseEntity<Object> updateMarkdownMeta(@Validated @RequestBody MarkdownMeta resources) {
        markdownMetaService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @ApiOperation("删除Markdown meta infos")
    public ResponseEntity<Object> deleteMarkdownMeta(@RequestBody Long[] ids) {
        markdownMetaService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}