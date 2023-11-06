package com.book.exception;

import com.book.result.ResultCodeEnum;

/**
 * api异常
 *
 * @author k
 * @date 2023/06/23
 */
public class ApiException extends RuntimeException {
    private final Integer code;

    public ApiException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
