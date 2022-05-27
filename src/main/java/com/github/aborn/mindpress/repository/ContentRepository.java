package com.github.aborn.mindpress.repository;

import com.github.aborn.mindpress.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author aborn
 * @date 2022-05-28
 **/
public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content> {
    /**
    * 根据 Articleid 查询
    * @param articleid /
    * @return /
    */
    Content findByArticleid(String articleid);
}