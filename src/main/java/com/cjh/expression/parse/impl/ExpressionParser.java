package com.cjh.expression.parse.impl;

import com.cjh.expression.Expression;
import com.cjh.expression.ExpressionChain;
import com.cjh.expression.exception.ParseException;
import com.cjh.expression.function.RegisterFunc;
import com.cjh.expression.parse.Parser;
import com.cjh.expression.symbol.RegisterSymbol;
import com.cjh.expression.util.TypeUtil;
import com.cjh.expression.var.ValueExpression;
import com.cjh.expression.var.VarExpression;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * @author JianhuiChen
 * @description 表达式解析器，负责将脚本解析成DSL表达式
 * @date 2019-11-26
 */
@Slf4j
public class ExpressionParser implements Parser<ExpressionChain> {

    /**
     * 脚本切割解析器
     */
    private final Parser<List<String>> singleScriptParser = new SingleScriptParser();

    /**
     * 运算符解析器
     */
    private final Parser<Queue<String>> symbolParser = new SymbolParser();

    /**
     * 函数参数解析器
     */
    private final Parser<List<String>> funcParser = new FuncParser();

    @Override
    public ExpressionChain parse(String script) {
        List<Expression> expressions = new LinkedList<>();
        for (String line : singleScriptParser.parse(script)) {
            try {
                expressions.add(parseExpression(line));
            } catch (Exception e) {
                e.printStackTrace();
                throw new ParseException(line, "Unknow exception");
            }
        }
        return new ExpressionChain(expressions);
    }

    /**
     * 解析单行脚本为表达式对象
     *
     * @param line 单行脚本
     * @return 脚本表达式
     * @throws Exception 解析中可能出现的异常
     */
    private Expression parseExpression(String line) throws Exception {
        Stack<Expression> stack = new Stack<>();
        Queue<String> chidrenScripts = symbolParser.parse(line);
        Expression expression = null;
        String script;
        while (!chidrenScripts.isEmpty()) {
            script = chidrenScripts.poll();
            RegisterSymbol symbol = RegisterSymbol.findRegisterSymbol(script);
            if (symbol == null) {
                if (isFuncScript(script)) {
                    String funcName = getFuncName(script);
                    RegisterFunc func = RegisterFunc.findRegisterFunc(funcName);
                    if (func == null) {
                        throw new Exception(String.format("Func %s not supports", funcName));
                    }
                    List<Expression> args = new LinkedList<>();
                    for (String arg : funcParser.parse(script)) {
                        args.add(parseExpression(arg));
                    }
                    expression = func.getExpression().getConstructor(List.class).newInstance(args);
                } else if (TypeUtil.isNumber(script)) {
                    expression = new ValueExpression(TypeUtil.toNumber(script));
                } else if (script.startsWith("\"")) {
                    expression = new ValueExpression(script.replaceAll("\"", ""));
                } else if (script.startsWith("'")) {
                    expression = new ValueExpression(script.replaceAll("'", ""));
                } else if (isSymbolScript(script)) {
                    expression = parseExpression(script);
                } else {
                    expression = new VarExpression(script.replaceAll(" ", ""));
                }
                stack.push(expression);
            } else {
                Expression right = stack.pop();
                Expression left = stack.pop();
                expression = symbol.getExpression().getConstructor(Expression.class, Expression.class).newInstance(left, right);
                stack.push(expression);
            }
        }
        return expression;
    }

    private boolean isSymbolScript(String script) {
        for (String symbol : RegisterSymbol.symbols()) {
            if (script.contains(symbol)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFuncScript(String script) {
        Pattern pattern = Pattern.compile("\\w+\\([\\s\\S]*\\)");
        return pattern.matcher(script).matches();
    }

    private String getFuncName(String funcScript) {
        return funcScript.substring(0, funcScript.indexOf("("));
    }
}
