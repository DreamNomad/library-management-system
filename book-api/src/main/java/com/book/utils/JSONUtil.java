package com.book.utils;

import com.book.exception.ApiException;
import com.book.result.ResultCodeEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JSONUtil {

    private static ObjectMapper objectMapper;

    @Autowired
    public JSONUtil(ObjectMapper objectMapper) {
        JSONUtil.objectMapper = objectMapper;
    }

    public static  <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtils.isBlank(text)){
            return null;
        }
        T t = null;
        try {
            t = objectMapper.readValue(text, clazz);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ApiException(ResultCodeEnum.JSON_ERROR);
        }
        return t;
    }

    public static String toJsonString(Object javaObject) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(javaObject);
        } catch (JsonProcessingException e) {
            throw new ApiException(ResultCodeEnum.JSON_ERROR);
        }
        return s;
    }
}
