package com.cjh.expression.util;

import java.util.regex.Pattern;

/**
 * @author JianhuiChen
 * @description 类型判断工具
 * @date 2019-11-25
 */
public class TypeUtil {

    /**
     * 对象是否为数字
     *
     * @param obj 对象
     * @return 是数字
     */
    public static boolean isNumber(Object obj) {
        Pattern pattern = Pattern.compile("-?[0-9]+.*[0-9]*");
        return pattern.matcher(obj.toString()).matches();
    }

    /**
     * 转换对象为数字
     *
     * @param obj 对象
     * @return 数字
     */
    public static double toNumber(Object obj) {
        return Double.parseDouble(obj.toString());
    }

    /**
     * 是否为boolean类型
     *
     * @param obj 对象
     * @return 是boolean类型
     */
    public static boolean isBoolean(Object obj) {
        return "true".equals(obj.toString()) || "false".equals(obj.toString()) || obj instanceof Boolean;
    }

    /**
     * 转换对象为boolean类型
     *
     * @param obj 对象
     * @return boolean类型对象
     */
    public static boolean toBoolean(Object obj) {
        return Boolean.parseBoolean(obj.toString());
    }
}
