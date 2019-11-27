package com.cjh.expression.function.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.function.FuncExpression;
import com.cjh.expression.util.TypeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author JianhuiChen
 * @description 求和函数
 * @date 2019-11-25
 */
public class SumFuncExpression extends FuncExpression {

    public SumFuncExpression(List<Expression> args) {
        super(args);
    }

    @Override
    public Object handle(RunningContext ctx) {
        List<Object> result = new LinkedList<>();
        for (Expression arg : getArgs()) {
            result.add(arg.intercept(ctx));
        }
        return result.stream().mapToDouble(TypeUtil::toNumber).sum();
    }
}
