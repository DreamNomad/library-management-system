package com.book.handler;

import com.book.exception.ApiException;
import com.book.result.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 全局异常处理程序
 *
 * @author k
 * @date 2023/06/24
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 使用ExceptionHandler注解声明处理Exception异常
     *
     * @param e e
     * @return {@link R}
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        // 控制台打印异常
        log.error(ExceptionUtils.getStackTrace(e));
        // 返回错误格式信息
        return R.error();
    }

    /**
     * HTTP请求方法不支持异常
     *
     * @param e e
     * @return {@link R}
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R exception(HttpRequestMethodNotSupportedException e) {
        // 控制台打印异常
        log.error(ExceptionUtils.getStackTrace(e));
        // 返回错误格式信息
        return R.error().setMessage(e.getMessage());
    }

    /**
     * 处理字段校验异常信息
     *
     * @param e e
     * @return {@link R}
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R exception(MethodArgumentNotValidException e) {
        // 控制台打印异常
        log.error(ExceptionUtils.getStackTrace(e));
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        // 返回错误格式信息
        return R.error().setMessage(allErrors.stream().findFirst().get().getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public R exception(ApiException e) {
        // 控制台打印异常
        log.error(ExceptionUtils.getStackTrace(e));
        // 返回错误格式信息
        return R.error().setMessage(e.getMessage()).setCode(e.getCode());
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R exception(HttpMessageNotReadableException e) {
        // 控制台打印异常
        log.error(ExceptionUtils.getStackTrace(e));
        // 返回错误格式信息
        return R.error().setMessage("请提供完整的数据字段!");
    }

}
