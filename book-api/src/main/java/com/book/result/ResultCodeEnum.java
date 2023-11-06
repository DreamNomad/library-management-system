package com.book.result;

import com.book.utils.StringUtil;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

/**
 * 常用返回的枚举
 *
 * @author k
 * @date 2023/06/23
 */
@Getter
@ToString
public enum ResultCodeEnum {
    /** 成功 */
    SUCCESS(true, 20000, StringUtils.EMPTY),
    ERROR(true, 50000, "失败"),
    SUCCESS_UPDATE(true, 30001, "更改成功"),
    SUCCESS_INSERT(true, 30002, "新增成功"),
    SUCCESS_DELETE(true, 30003, "删除成功"),
    /** 未知错误 */
    UNKNOWN_REASON(false, 20001, "未知错误"),
    /** 序列化错误 */
    JSON_ERROR(false, 20002, "序列化错误"),
    /** 未登录 */
    USER_NOT_LOGIN(false, 20003, "未登录"),
    /** token过期 */
    TOKEN_EXPIRED(false, 20004, "token过期"),
    /** 用户名不存在 */
    USERNAME_NOT_EXIST(false, 20005, "用户不存在"),
    /** 用户名已存在 */
    USERNAME_EXIST(false, 20006, "用户名已存在"),
    /** 账户或密码错误 */
    ERROR_INVALID_CREDENTIALS(false, 20007, "账户或密码错误");

    private final Boolean success;
    private final Integer code;
    private final String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
