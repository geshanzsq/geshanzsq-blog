package com.geshanzsq.client.common.blog.po.about;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 关于我
 *
 * @author geshanzsq
 * @date 2022/10/28
 */
@Data
public class BlogAboutMe implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关于 id
     */
    @TableId
    private Long id;

    /**
     * 内容
     */
    private String content;

    /**
     * 编辑器类型（1 markdown编辑器，2 富文本编辑器）
     */
    private Integer editorType;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 创建人用户 id
     */
    @TableField("fk_create_user_id")
    private Long createUserId;

    /**
     * 修改时间
     */
    private Date gmtModify;

    /**
     * 修改人用户 id
     */
    @TableField("fk_modify_user_id")
    private Long modifyUserId;

}
