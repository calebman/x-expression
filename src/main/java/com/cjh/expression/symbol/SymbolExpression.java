package com.cjh.expression.symbol;

import com.cjh.expression.Expression;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author JianhuiChen
 * @description 运算符表达式
 * @date 2019-11-25
 * @see RegisterSymbol 内部注册可用的表达式
 */
@RequiredArgsConstructor
@Getter
public abstract class SymbolExpression<R> implements Expression<R> {

    /**
     * 左表达式
     */
    private final Expression left;

    /**
     * 右表达式
     */
    private final Expression right;
}
