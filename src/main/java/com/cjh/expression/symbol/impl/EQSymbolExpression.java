package com.cjh.expression.symbol.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.exception.InterceptException;
import com.cjh.expression.symbol.SymbolExpression;
import com.cjh.expression.util.TypeUtil;

/**
 * @author JianhuiChen
 * @description 相等运算
 * @date 2019-11-25
 */
public class EQSymbolExpression extends SymbolExpression<Boolean> {

    public EQSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Boolean intercept(RunningContext ctx) {
        return compute(getLeft().intercept(ctx), getRight().intercept(ctx));
    }

    private Boolean compute(Object left, Object right) {
        return left == right;
    }
}
