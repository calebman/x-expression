package com.cjh.expression;

import com.cjh.expression.ctx.RunningContext;

/**
 * @author JianhuiChen
 * @description 表达式对象
 * @date 2019-11-25
 * @see ExpressionChain 表达式执行链
 * @see com.cjh.expression.symbol.SymbolExpression 运算符表达式
 * @see com.cjh.expression.function.FuncExpression 函数表达式
 */
public interface Expression<R> {

    /**
     * 执行表达式
     *
     * @param ctx 上下文信息
     * @return 执行结果
     */
    R intercept(RunningContext ctx);
}
