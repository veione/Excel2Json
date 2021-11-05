package com.think.tool.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;

public abstract class TypeUtils {

    public static Object toValue(Object value) {
        if (NumberUtil.isInteger(value.toString())) {
            return Convert.toInt(value);
        } else if (NumberUtil.isLong(value.toString())) {
            return Convert.toLong(value);
        } else if (NumberUtil.isDouble(value.toString())) {
            return Convert.toDouble(value);
        }
        return value;
    }
}
