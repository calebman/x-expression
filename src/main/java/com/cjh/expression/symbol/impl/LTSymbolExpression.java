package com.cjh.expression.symbol.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.exception.InterceptException;
import com.cjh.expression.symbol.SymbolExpression;
import com.cjh.expression.util.TypeUtil;

/**
 * @author JianhuiChen
 * @description  小于运算 只支持数字
 * @date 2019-11-25
 */
public class LTSymbolExpression extends SymbolExpression<Boolean> {

    public LTSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean intercept(RunningContext ctx) {
        return compute(getLeft().intercept(ctx), getRight().intercept(ctx));
    }

    private Boolean compute(Object left, Object right) {
        if (TypeUtil.isNumber(left) && TypeUtil.isNumber(right)) {
            return TypeUtil.toNumber(left) < TypeUtil.toNumber(right);
        }
        throw new InterceptException(this, "Less then must act on numbers");
    }
}
