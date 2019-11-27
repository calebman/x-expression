package com.cjh.expression.function.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.function.FuncExpression;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JianhuiChen
 * @description 打印函数，将参数全部打印并通过,分隔
 * @date 2019-11-25
 */
public class PrintFuncExpression extends FuncExpression {

    public PrintFuncExpression(List<Expression> args) {
        super(args);
    }

    @Override
    public Object handle(RunningContext ctx) {
        List<Object> result = new LinkedList<>();
        for (Expression arg : getArgs()) {
            result.add(arg.intercept(ctx));
        }
        System.out.println(result.stream().map(Object::toString).collect(Collectors.joining(" , ")));
        return null;
    }
}
