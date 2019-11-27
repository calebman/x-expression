package com.cjh.expression.exception;

import com.cjh.expression.Expression;
import lombok.Getter;

/**
 * @author JianhuiChen
 * @description 表达式执行异常
 * @date 2019-11-25
 */
@Getter
public class InterceptException extends RuntimeException {

    /**
     * 当前执行异常的表达式
     */
    private final Expression expression;

    public InterceptException(Expression expression, String detail) {
        super(String.format("Expression execute failed [%s]", detail));
        this.expression = expression;
    }
}
