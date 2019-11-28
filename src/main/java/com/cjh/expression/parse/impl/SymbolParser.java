package com.cjh.expression.parse.impl;

import com.cjh.expression.parse.Parser;
import com.cjh.expression.symbol.RegisterSymbol;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author JianhuiChen
 * @description 运算符解析器，负责根据运算符及其优先级将运算符由中缀转后缀表达式
 * a = b + c * d => [a, b, d, c, *, +, =]
 * @date 2019-11-26
 */
public class SymbolParser implements Parser<Queue<String>> {

    @Override
    public Queue<String> parse(String script) {
        // 去头尾空格
        script = script.trim();
        script = this.preProcessScript(script);
        Stack<String> symbolStack = new Stack<>();
        Queue<String> result = new LinkedList<>();
        List<char[]> symbols = RegisterSymbol.symbolChars();
        StringBuilder sb = new StringBuilder();
        int recordBrackets = 0;
        char[] chars = script.toCharArray();
        char c;
        for (int i = 0; i < chars.length; i++) {
            c = chars[i];
            if (c == 32) {
                continue;
            }
            if (c == '(') {
                recordBrackets++;
                sb.append(c);
                continue;
            } else if (c == ')') {
                recordBrackets--;
                sb.append(c);
                continue;
            }
            if (recordBrackets > 0) {
                sb.append(c);
            } else {
                boolean isSymbol = false;
                String curSymbol = String.valueOf(c);
                Stack<String> stringStack = new Stack<>();
                stringStack.push(curSymbol);
                int j, temp = 0;
                while (!stringStack.isEmpty()) {
                    int offset = 0;
                    String matchSymbol = stringStack.pop();
                    j = temp;
                    boolean finish = false;
                    while (j < symbols.size()) {
                        char[] symbolChar = symbols.get(j);
                        if (isSameSymbol(symbolChar, matchSymbol)) {
                            // 精确匹配
                            isSymbol = true;
                            curSymbol = matchSymbol;
                            if (offset > 0) {
                                i = i + offset;
                            }
                            finish = true;
                            break;
                        } else if (isSameStart(symbolChar, matchSymbol)) {
                            // 前缀匹配
                            ++offset;
                            if ((i + offset) >= chars.length) {
                                break;
                            }
                            temp = j + 1;
                            stringStack.push(matchSymbol);
                            matchSymbol = matchSymbol + chars[(i + offset)];
                            j = 0;
                        } else {
                            j++;
                        }
                    }
                    if (finish) {
                        break;
                    }
                }
                if (isSymbol) {
                    curSymbol = curSymbol.trim();
                    result.add(sb.toString());
                    sb = new StringBuilder();
                    // 碰到符号
                    while (!symbolStack.isEmpty()) {
                        int prevPriority = RegisterSymbol.findRegisterSymbol(symbolStack.peek()).getPriority();
                        int curPriority = RegisterSymbol.findRegisterSymbol(curSymbol).getPriority();
                        if (prevPriority >= curPriority) {
                            result.add(symbolStack.pop());
                        } else {
                            break;
                        }
                    }
                    symbolStack.push(curSymbol);
                } else {
                    recordBrackets = 0;
                    int brackets = 0;
                    boolean doubleQuotesEnd = true;
                    boolean singleQuotesEnd = true;
                    while (i < chars.length) {
                        c = chars[i];
                        sb.append(c);
                        if (c == '\"') {
                            doubleQuotesEnd = !doubleQuotesEnd;
                        } else if (c == '\'') {
                            singleQuotesEnd = !singleQuotesEnd;
                        }
                        if (doubleQuotesEnd && singleQuotesEnd) {
                            if (c == '(') {
                                brackets++;
                            } else if (c == ')') {
                                if (brackets > 0) {
                                    brackets--;
                                } else {
                                    --i;
                                    break;
                                }
                            }
                            if (brackets == 0) {
                                break;
                            }
                        }
                        ++i;
                    }
                }
            }
        }
        result.add(sb.toString());
        while (!symbolStack.isEmpty()) {
            result.add(symbolStack.pop());
        }
        return result;
    }

    /**
     * 预处理脚本
     *
     * @param script 脚本信息
     * @description 处理示例
     * a + b * c => a + b * c
     * (a + b) => a + b
     * (a + b * (c + d)) => a + b * (c + d)
     * (a + b) * (c + d) => (a + b) * (c + d)
     * @return 去除两边匹配的括号
     */
    private String preProcessScript(String script) {
        if (script.startsWith("(") && script.endsWith(")")) {
            char[] chars = script.toCharArray();
            Stack<Character> stack = new Stack<>();
            char c;
            int endMatch = 0;
            int i = 0;
            boolean doubleQuotesEnd = true;
            boolean singleQuotesEnd = true;
            while (i < chars.length) {
                c = chars[i];
                if (c == '\"') {
                    doubleQuotesEnd = !doubleQuotesEnd;
                } else if (c == '\'') {
                    singleQuotesEnd = !singleQuotesEnd;
                }
                if (doubleQuotesEnd && singleQuotesEnd) {
                    if (c == '(') {
                        stack.push(c);
                    } else if (c == ')') {
                        stack.pop();
                        if (stack.isEmpty()) {
                            endMatch = i;
                            break;
                        }
                    }
                }
                i++;
            }
            if (endMatch == chars.length - 1) {
                return script.substring(1, script.length() - 1);
            }
        }
        return script;
    }

    private boolean isSameStart(char[] symbolChar, String str) {
        char[] strChar = str.trim().toCharArray();
        if (strChar.length > symbolChar.length) {
            return false;
        }
        for (int i = 0; i < strChar.length; i++) {
            if (symbolChar[i] != strChar[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean isSameSymbol(char[] symbolChar, String str) {
        char[] strChar = str.trim().toCharArray();
        if (symbolChar.length == strChar.length) {
            for (int i = 0; i < symbolChar.length; i++) {
                if (symbolChar[i] != strChar[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
