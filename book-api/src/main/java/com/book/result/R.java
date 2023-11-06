package com.book.result;

import com.book.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 统一返回格式
 *
 * @author k
 * @date 2023/06/23
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class R<T> {
    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回的消息
     */
    private String message;

    /**
     * 放置响应的数据
     */
    private T data;

    public R() {}

    public R(ResultCodeEnum resultCodeEnum){
        this.success = resultCodeEnum.getSuccess();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public R(ResultCodeEnum resultCodeEnum, T data){
        this.data = data;
        this.success = resultCodeEnum.getSuccess();
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    /** 以下是定义一些常用到的格式，可以看到调用了我们创建的枚举类 */

    public static R ok() {
        return ok(null);
    }

    public static <T> R<T> ok(T data) {
        return ok(ResultCodeEnum.SUCCESS, data);
    }

    public static <T> R<T> ok(ResultCodeEnum resultCodeEnum, T data) {
        return new R<T>(resultCodeEnum, data);
    }

    public static R updateOk() {
        return updateOk(null);
    }

    public static <T> R<T> updateOk(T data) {
        return new R<T>(ResultCodeEnum.SUCCESS_UPDATE, data);
    }

    public static R insertOk() {
        return insertOk(null);
    }

    public static <T> R<T> insertOk(T data) {
        return new R<T>(ResultCodeEnum.SUCCESS_INSERT, data);
    }

    public static R deleteOk() {
        return deleteOk(null);
    }

    public static <T> R<T> deleteOk(T data) {
        return new R<T>(ResultCodeEnum.SUCCESS_DELETE, data);
    }

    public static R error(ResultCodeEnum resultCodeEnum) {
        return new R(resultCodeEnum);
    }

    public static R error() {
        return error(ResultCodeEnum.UNKNOWN_REASON);
    }

    /**
     * 是否成功
     * @param bool
     * @return {@link R}
     */
    public static R isOk(boolean bool){
        return isOk(bool, null);
    }

    public static <T> R<T> isOk(boolean bool,T data){
        validation(bool);
        return ok(data);
    }


    /**
     * 是否更新成功
     * @param bool
     * @return {@link R}
     */
    public static R isUpdateOk(boolean bool){
        return isUpdateOk(bool, null);
    }

    public static <T> R<T> isUpdateOk(boolean bool,T data){
        validation(bool);
        return updateOk(data);
    }

    /**
     * 是否插入成功
     * @param bool
     * @return {@link R}
     */
    public static R isInsertOk(boolean bool){
        return isInsertOk(bool, null);
    }

    public static <T> R<T> isInsertOk(boolean bool,T data){
        validation(bool);
        return insertOk(data);
    }

    /**
     * 是否删除成功
     * @param bool
     * @return {@link R}
     */
    public static R isDeleteOk(boolean bool){
        return isDeleteOk(bool, null);
    }

    public static <T> R<T> isDeleteOk(boolean bool,T data){
        validation(bool);
        return deleteOk(data);
    }

    public static R setResult(ResultCodeEnum resultCodeEnum) {
        return new R(resultCodeEnum);
    }

    public static <T> R<T> setResult(ResultCodeEnum resultCodeEnum,T data) {
        return new R<T>(resultCodeEnum, data);
    }

    private static void validation(boolean bool){
        if (!bool){
            throw new ApiException(ResultCodeEnum.ERROR);
        }
    }
}
