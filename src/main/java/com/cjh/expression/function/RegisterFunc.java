package com.cjh.expression.function;

import com.cjh.expression.function.impl.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author JianhuiChen
 * @description 函数表达式注册器
 * @date 2019-11-25
 */
@RequiredArgsConstructor
@Getter
public enum RegisterFunc {
    ADD("print", PrintFuncExpression.class),
    SUB("return", ReturnFuncExpression.class),
    SUM("sum", SumFuncExpression.class),
    MAX("max", MaxFuncExpression.class),
    MIN("min", MinFuncExpression.class);

    /**
     * 函数名称
     */
    private final String name;

    /**
     * 执行器
     */
    private final Class<? extends FuncExpression> expression;

    public static boolean isRegisterFunc(String symbol) {
        for (RegisterFunc value : values()) {
            if (value.getName().equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public static RegisterFunc findRegisterFunc(String symbol) {
        for (RegisterFunc value : values()) {
            if (value.getName().equals(symbol)) {
                return value;
            }
        }
        return null;
    }
}
