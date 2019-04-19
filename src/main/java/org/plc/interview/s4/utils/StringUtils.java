package org.plc.interview.s4.utils;

public class StringUtils {
    public static boolean isEmpty(String string) {
        return string == null ? true : string.isEmpty();
    }

    public static boolean isNotEmpty(String string) {
        return string != null ? !string.isEmpty() : false;
    }
}
