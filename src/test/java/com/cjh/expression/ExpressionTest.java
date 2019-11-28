package com.cjh.expression;

import com.cjh.expression.ctx.RunningContext;
import com.cjh.expression.parse.Parser;
import com.cjh.expression.parse.impl.ExpressionParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author JianhuiChen
 * @description 表达式运行测试
 * @date 2019-11-25
 */
@Slf4j
public class ExpressionTest {

    private final Parser<ExpressionChain> parser = new ExpressionParser();

    /**
     * 测试运算符 以及 优先级 + - * / ^
     */
    @Test
    public void testOperation() {
        String script = "c = a + b * c - 2 ^ a + b / 2;return(c);";
        executeScript(script);
    }

    /**
     * 测试函数 sun max min return
     */
    @Test
    public void testFunc() {
        String script = "d = sum(max(a,b,c),20)+b^c*2;return(d);print(d);d = b+c;return(c);";
        executeScript(script);
    }

    /**
     * 测试单引号/双引号 ' "
     */
    @Test
    public void testQuotes() {
        String script = "c = ((a+b)*c);print(c)\nd=123;print('set good ,bb;+-/*\n  d = ' , d * b >= 2,'a,b ' + (a < 3 && b >= 3));print(b-c,min(a,b,c));";
        executeScript(script);
    }

    /**
     * 测试双目运算符 && || >= <=
     */
    @Test
    public void testTwoOperation() {
        String script = "d =  a+b*c > 12 && a >= 1;return(d);";
        executeScript(script);
    }

    private void executeScript(String script) {
        log.info("---------------execute script---------------");
        System.out.println(script);
        log.info("---------------execute script---------------");

        ExpressionChain expressionChain = parser.parse(script);
        RunningContext context = new RunningContext();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 6);
        log.info("execute context {}", context);
        log.info("script result {}", expressionChain.intercept(context));
    }
}
