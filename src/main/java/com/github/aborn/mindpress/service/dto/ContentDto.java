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
package com.github.aborn.mindpress.service.dto;

import com.sancaiwulian.smes.inf.base.BaseDTO;
import lombok.Data;
import java.io.Serializable;

/**
 * @author aborn
 * @website https://sancaiwulian.com
 * @description /
 * @date 2022-05-28
 **/
@Data
public class ContentDto extends BaseDTO implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 文章唯一标识
     */
    private String articleid;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章描述
     */
    private String desc;

    /**
     * Markdown文本内容
     */
    private String content;
}