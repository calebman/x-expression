package com.cjh.expression.var;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.exception.InterceptException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author JianhuiChen
 * @description 变量表达式
 * @date 2019-11-25
 */
@RequiredArgsConstructor
@Getter
public class VarExpression implements Expression {

    private final String key;

    @Override
    public Object intercept(RunningContext ctx) {
        if (!ctx.containsKey(key)) {
            throw new InterceptException(this, key + " is undefined");
        }
        return ctx.get(key.trim());
    }
}
