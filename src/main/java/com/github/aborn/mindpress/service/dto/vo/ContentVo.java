package com.github.aborn.mindpress.service.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.aborn.mindpress.service.dto.ContentDto;
import com.github.aborn.mindpress.service.dto.MarkdownMetaDto;
import lombok.Data;

import java.io.Serializable;

/**
 * @author aborn
 * @date 2022/05/30 21:37
 */
@Data
public class ContentVo extends ContentDto implements Serializable {
    private static final long serialVersionUID = 2371591328857612229L;

    public ContentVo(ContentDto dto, MarkdownMetaDto metaDto) {
        BeanUtil.copyProperties(dto, this, CopyOptions.create().setIgnoreNullValue(true));
        if (metaDto != null) {
            BeanUtil.copyProperties(metaDto, this, CopyOptions.create().setIgnoreNullValue(true));
        }
        this.pub = metaDto.getIsPublic() != 0;
    }

    /**
     * title
     */
    private String title;

    /**
     * description
     */
    private String desc;

    /**
     * description
     */
    private String tags;

    /**
     * the space file belongs to
     */
    private String space;

    /**
     * is public
     */
    private boolean pub;
}
