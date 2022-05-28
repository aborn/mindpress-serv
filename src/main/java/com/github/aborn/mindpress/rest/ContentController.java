package com.github.aborn.mindpress.rest;

import com.github.aborn.mindpress.domain.Content;
import com.github.aborn.mindpress.service.ContentService;
import com.github.aborn.mindpress.service.dto.ContentQueryCriteria;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author aborn
 * @date 2022-05-28
 **/
@RestController
@RequiredArgsConstructor
@Api(tags = "Markdown file content controller")
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService contentService;

    @GetMapping
    public ResponseEntity<Object> queryContent(ContentQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(contentService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createContent(@Validated @RequestBody Content resources) {
        return new ResponseEntity<>(contentService.create(resources), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updateContent(@Validated @RequestBody Content resources) {
        contentService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteContent(@RequestBody Long[] ids) {
        contentService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}