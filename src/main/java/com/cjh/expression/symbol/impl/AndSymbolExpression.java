package com.cjh.expression.symbol.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.exception.InterceptException;
import com.cjh.expression.symbol.SymbolExpression;
import com.cjh.expression.util.TypeUtil;

/**
 * @author JianhuiChen
 * @description 与运算 只支持boolean
 * @date 2019-11-25
 */
public class AndSymbolExpression extends SymbolExpression<Boolean> {

    public AndSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean intercept(RunningContext ctx) {
        return compute(getLeft().intercept(ctx), getRight().intercept(ctx));
    }

    private Boolean compute(Object left, Object right) {
        if (TypeUtil.isBoolean(left) && TypeUtil.isBoolean(right)) {
            return TypeUtil.toBoolean(left) && TypeUtil.toBoolean(right);
        }
        throw new InterceptException(this, "And must act on boolean");
    }
}
