package com.book.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author k
 * @since 2023-06-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图书名称
     */
    @NotBlank(message = "图书名称不能为空")
    @TableField("title")
    private String title;

    /**
     * 图书分类
     */
    @TableField("type")
    private Integer type;

    /**
     * 图书图片
     */
    @TableField("image_path")
    private String imagePath;

    /**
     * 图书价格
     */
    @Digits(integer = 10, fraction = 2)
    @TableField("price")
    private BigDecimal price;

    /**
     * 图书介绍
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 图书库存
     */
    @TableField("count")
    private Integer count;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "delete_time")
    @TableLogic
    private LocalDateTime deleteTime;

    /**
     * 状态
     */
    @TableField("status")
    private Boolean status;
}
