package com.cjh.expression.function.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.function.FuncExpression;
import com.cjh.expression.util.TypeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author JianhuiChen
 * @description 取最小值函数
 * @date 2019-11-25
 */
public class MinFuncExpression extends FuncExpression {

    public MinFuncExpression(List<Expression> args) {
        super(args);
    }

    @Override
    public Object handle(RunningContext ctx) {
        List<Object> result = new LinkedList<>();
        for (Expression arg : getArgs()) {
            result.add(arg.intercept(ctx));
        }
        return result.stream().max((o1, o2) -> {
            if (TypeUtil.isNumber(o1) && TypeUtil.isNumber(o2)) {
                return (int) (TypeUtil.toNumber(o2) - TypeUtil.toNumber(o1));
            }
            return o2.toString().length() - o1.toString().length();
        }).get();
    }
}
