package com.github.aborn.mindpress.service.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aborn.mindpress.domain.Content;
import com.github.aborn.mindpress.service.dto.ContentDto;
import com.github.aborn.mindpress.service.dto.MarkdownMetaDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aborn
 * @date 2022/05/30 21:37
 */
@Data
public class ContentVo extends MarkdownMetaDto implements Serializable {

    private static final long serialVersionUID = 2371591328857612229L;

    public ContentVo() {
    }

    public ContentVo(ContentDto dto, MarkdownMetaDto metaDto) {
        BeanUtil.copyProperties(metaDto, this, CopyOptions.create().setIgnoreNullValue(true));

        CopyOptions copyOptions = CopyOptions.create();
        copyOptions.setIgnoreNullValue(true);
        // 以下4个字段，以 meta表里的数据为准
        copyOptions.setIgnoreProperties("createBy", "updateBy", "createTime", "updateTime");
        BeanUtil.copyProperties(dto, this, copyOptions);

        this.pub = metaDto.getIsPublic() != 0;
    }

    @JsonIgnore
    public Content getContentDomain() {
        Content content = new Content();
        content.copyFromVo(this);
        return content;
    }

    private String content;

    /**
     * is public
     */
    private boolean pub;
}
