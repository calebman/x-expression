package com.cjh.expression.symbol.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.exception.InterceptException;
import com.cjh.expression.var.ValueExpression;
import com.cjh.expression.symbol.SymbolExpression;
import com.cjh.expression.var.VarExpression;
import lombok.Getter;

/**
 * @author JianhuiChen
 * @description 赋值
 * @date 2019-11-25
 */
@Getter
public class AssiSymbolExpression extends SymbolExpression {

    private final ValueExpression left;

    public AssiSymbolExpression(Expression left, Expression right) {
        super(left, right);
        if (left instanceof ValueExpression) {
            this.left = (ValueExpression) left;
        } else if (left instanceof VarExpression) {
            this.left = new ValueExpression(((VarExpression) left).getKey());
        } else {
            throw new InterceptException(this, "AssiSymbolExpression left type must be ValueExpression or VarExpression");
        }
    }

    @Override
    public Object intercept(RunningContext ctx) {
        return ctx.put(getLeft().intercept(ctx).toString(), getRight().intercept(ctx));
    }
}
