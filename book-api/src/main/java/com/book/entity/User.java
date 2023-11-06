package com.book.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @TableField("account")
    private String account;

    /**
     * 密码
     */
    @JsonIgnore
    @TableField("password")
    private String password;

    /**
     * 盐
     */
    @JsonIgnore
    @TableField("salt")
    private String salt;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 性别：0(男) 1(女)
     */
    @TableField("sex")
    private Boolean sex;

    /**
     * 出生日期
     */
    @TableField("birth")
    private LocalDate birth;

    /**
     * 电子邮箱
     */
    @TableField("mail")
    private String mail;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 送货地址
     */
    @TableField("site")
    private String site;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
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
