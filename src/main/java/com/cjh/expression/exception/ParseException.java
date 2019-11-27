package com.cjh.expression.exception;

import lombok.Getter;

/**
 * @author JianhuiChen
 * @description 解析器异常
 * @date 2019-11-25
 */
@Getter
public class ParseException extends RuntimeException {

    /**
     * 当前解析异常的脚本
     */
    private final String script;

    public ParseException(String script, String detail) {
        super(String.format("Script %s parse failed [%s]", script, detail));
        this.script = script;
    }
}
