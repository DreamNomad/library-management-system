package com.book.utils;

public class StringUtil {

    public static String emptyToNull(Object value) {
        return emptyToNull(value, true);
    }

    public static String emptyToNull(Object value, boolean trimToNull) {
        return value == null || (trimToNull && value.toString().trim().length() <= 0) ? null : value.toString();
    }

    public static String nullToEmpty(Object value) {
        return nullToEmpty(value, true);
    }

    public static String nullToEmpty(Object value, boolean trimToNull) {
        return value == null ? "" : (trimToNull ? value.toString().trim() : value.toString());
    }
}
