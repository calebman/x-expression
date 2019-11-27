package com.cjh.expression;

import com.cjh.expression.ctx.RunningContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author JianhuiChen
 * @description 表达式执行链，一系列脚本可解析成一个执行链，通过ExpressionParser对象进行解析
 * @date 2019-11-25
 * @see com.cjh.expression.parse.impl.ExpressionParser 执行链解析器
 */
@RequiredArgsConstructor
public class ExpressionChain implements Expression {

    /**
     * 表达式列表
     */
    private final List<Expression> expressions;

    @Override
    public Object intercept(RunningContext ctx) {
        for (Expression expression : expressions) {
            if (ctx.isComplete()) {
                return ctx.getResult();
            }
            expression.intercept(ctx);
        }
        return ctx.getResult();
    }
}
