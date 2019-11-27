package com.cjh.expression.function;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author JianhuiChen
 * @description 函数表达式
 * @date 2019-11-25
 * @see RegisterFunc 内部注册可用的函数表达式
 */
@RequiredArgsConstructor
@Getter
public abstract class FuncExpression implements Expression {

    /**
     * 参数列表
     */
    private final List<Expression> args;

    @Override
    public Object intercept(RunningContext ctx) {
        if (ctx.isComplete()) {
            return ctx.getResult();
        }
        return handle(ctx);
    }

    /**
     * 函数执行内容
     *
     * @param ctx 上下文信息
     * @return 执行结果
     */
    public abstract Object handle(RunningContext ctx);
}
