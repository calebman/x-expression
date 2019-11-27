package com.cjh.expression.function.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.function.FuncExpression;
import com.cjh.expression.var.ValueExpression;
import lombok.Getter;

import java.util.List;

/**
 * @author JianhuiChen
 * @description 结束脚本执行的函数
 * @date 2019-11-25
 */
@Getter
public class ReturnFuncExpression extends FuncExpression {

    /**
     * 函数执行返回的表达式
     */
    private final Expression returnExpression;

    public ReturnFuncExpression(List<Expression> args) {
        super(args);
        if (args.size() < 1) {
            this.returnExpression = new ValueExpression(null);
        } else {
            this.returnExpression = args.get(0);
        }
    }

    @Override
    public Object handle(RunningContext ctx) {
        Object result = returnExpression.intercept(ctx);
        ctx.setComplete(true);
        ctx.setResult(result);
        return result;
    }
}
