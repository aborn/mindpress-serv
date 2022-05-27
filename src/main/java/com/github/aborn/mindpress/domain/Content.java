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
package com.github.aborn.mindpress.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.sancaiwulian.smes.inf.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
 * @author aborn
 * @website https://sancaiwulian.com
 * @description /
 * @date 2022-05-28
 **/
@Entity
@Data
@Table(name = "md_content")
public class Content extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private Long id;

    @Column(name = "articleid",unique = true,nullable = false)
    @NotBlank
    @ApiModelProperty(value = "文章唯一标识")
    private String articleid;

    @Column(name = "title")
    @ApiModelProperty(value = "文章标题")
    private String title;

    @Column(name = "desc",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "文章描述")
    private String desc;

    @Column(name = "content")
    @ApiModelProperty(value = "Markdown文本内容")
    private String content;

    public void copy(Content source) {
        BeanUtil.copyProperties(source, this, CopyOptions.create().setIgnoreNullValue(true));
    }
}