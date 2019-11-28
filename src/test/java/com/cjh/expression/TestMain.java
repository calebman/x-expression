package com.cjh.expression;

import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.parse.Parser;
import com.cjh.expression.parse.impl.ExpressionParser;

/**
 * @author JianhuiChen
 * @description 测试主函数
 * @date 2019-11-27
 */
public class TestMain {

    public static void main(String[] args) {
        String script = "c = a + b * c - 2 ^ a + b / 2;return(c);";
        script = "print('result = ' , ('aa)(a((((((((' + ((a/b >= 0.1 && a/b < 0.3) || (c/b >=0.3 && c/b < 0.5))));";
        // 获得解析器
        Parser<ExpressionChain> parser = new ExpressionParser();
        // 构建上下文
        RunningContext context = new RunningContext();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 6);
        // 解析脚本
        ExpressionChain expressionChain = parser.parse(script);
        // 执行表达式
        Object result = expressionChain.intercept(context);
        System.out.println(result);
    }
}
