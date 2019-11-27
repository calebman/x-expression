package com.cjh.expression.var;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import lombok.RequiredArgsConstructor;

/**
 * @author JianhuiChen
 * @description 值表达式 代表字符串/数字等
 * @date 2019-11-25
 */
@RequiredArgsConstructor
public class ValueExpression implements Expression<Object> {

    private final Object value;

    @Override
    public Object intercept(RunningContext ctx) {
        return value;
    }
}
