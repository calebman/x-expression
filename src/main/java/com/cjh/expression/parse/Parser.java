package com.cjh.expression.parse;

import com.cjh.expression.exception.ParseException;

/**
 * @param <R> 解析结果类型
 * @author JianhuiChen
 * @description 解析器
 * @date 2019-11-26
 */
public interface Parser<R> {

    /**
     * 解析脚本为指定结果类型
     *
     * @param script 脚本
     * @return 解析结果
     * @throws ParseException 解析异常信息
     */
    R parse(String script) throws ParseException;
}
