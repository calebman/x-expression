package com.cjh.expression.symbol.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.exception.InterceptException;
import com.cjh.expression.symbol.SymbolExpression;
import com.cjh.expression.util.TypeUtil;

/**
 * @author JianhuiChen
 * @description
 * @date 2019-11-25
 */
public class SubSymbolExpression extends SymbolExpression<Double> {

    public SubSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Double intercept(RunningContext ctx) {
        return compute(getLeft().intercept(ctx), getRight().intercept(ctx));
    }

    private Double compute(Object left, Object right) {
        if (TypeUtil.isNumber(left) && TypeUtil.isNumber(right)) {
            return TypeUtil.toNumber(left) - TypeUtil.toNumber(right);
        }
        throw new InterceptException(this, "Subtraction must act on numbers");
    }
}
