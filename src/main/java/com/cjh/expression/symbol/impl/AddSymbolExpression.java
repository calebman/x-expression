package com.cjh.expression.symbol.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.symbol.SymbolExpression;
import com.cjh.expression.util.TypeUtil;

/**
 * @author JianhuiChen
 * @description 加法运算 支持数字或字符串相加
 * @date 2019-11-25
 */
public class AddSymbolExpression extends SymbolExpression {

    public AddSymbolExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public Object intercept(RunningContext ctx) {
        return compute(getLeft().intercept(ctx), getRight().intercept(ctx));
    }

    private Object compute(Object left, Object right) {
        if (TypeUtil.isNumber(left) && TypeUtil.isNumber(right)) {
            return TypeUtil.toNumber(left) + TypeUtil.toNumber(right);
        }
        return left.toString() + right.toString();
    }
}
