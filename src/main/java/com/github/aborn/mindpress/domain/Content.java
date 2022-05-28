package com.github.aborn.mindpress.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.aborn.mindpress.inf.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author aborn
 * @description /
 * @date 2022-05-28
 **/
@Entity
@Data
@Table(name = "md_markdown_content")
public class Content extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "articleid",unique = true,nullable = false)
    @NotBlank
    @ApiModelProperty(value = "article id")
    private String articleid;

    @Column(name = "content")
    @ApiModelProperty(value = "Markdown content")
    private String content;

    public void copy(Content source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}