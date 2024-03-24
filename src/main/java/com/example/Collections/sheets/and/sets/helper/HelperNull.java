package com.example.Collections.sheets.and.sets.helper;

import org.apache.commons.lang3.StringUtils;

public class HelperNull {

    public static boolean isEmptyString(String string) {
        return string == null || StringUtils.isEmpty(string);
    }
}
