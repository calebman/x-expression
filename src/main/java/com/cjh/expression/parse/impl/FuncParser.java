package com.cjh.expression.parse.impl;

import com.cjh.expression.parse.Parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author JianhuiChen
 * @description 函数参数解析器 将函数中的所有参数提取
 * max(1, 2, 3) => [1, 2, 3]
 * sum(max(1, 2), min(4, 2), 3) => [max(1, 2), min(4, 2), 3]
 * @date 2019-11-26
 */
public class FuncParser implements Parser<List<String>> {

    @Override
    public List<String> parse(String script) {
        List<String> result = new LinkedList<>();
        Stack<String> semicolonStack = new Stack<>();
        Stack<String> stack = new Stack<>();
        // 去头尾空格
        script = script.trim();
        // 去函数名
        script = script.substring(script.indexOf("("));
        // 去头尾括号
        if (script.startsWith("(") && script.endsWith(")")) {
            script = script.substring(1, script.length() - 1);
        }
        String[] exps = script.split("");
        StringBuilder sb = new StringBuilder();
        for (String s : exps) {
            switch (s) {
                case "\"":
                    if (!semicolonStack.isEmpty() && semicolonStack.peek().equals("\"")) {
                        semicolonStack.pop();
                    } else {
                        semicolonStack.push("\"");
                    }
                    break;
                case "'":
                    if (!semicolonStack.isEmpty() && semicolonStack.peek().equals("'")) {
                        semicolonStack.pop();
                    } else {
                        semicolonStack.push("'");
                    }
                    break;
                case "(":
                    if (semicolonStack.isEmpty()) {
                        stack.push("(");
                    }
                    break;
                case ")":
                    if (semicolonStack.isEmpty()) {
                        stack.pop();
                    }
                    break;
                case ",":
                    if (stack.isEmpty() && semicolonStack.isEmpty()) {
                        result.add(sb.toString());
                        sb = new StringBuilder();
                        continue;
                    }
                    break;
            }
            sb.append(s);
        }
        result.add(sb.toString());
        return result;
    }
}
