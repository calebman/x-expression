package com.cjh.expression.parse.impl;

import com.cjh.expression.parse.Parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author JianhuiChen
 * @description 脚本切割解析 根据;/\n将脚本切割成单行
 * a = b + c;return(a); => [a = b + c, return(a)]
 * @date 2019-11-26
 */
public class SingleScriptParser implements Parser<List<String>> {

    @Override
    public List<String> parse(String script) {
        List<String> result = new LinkedList<>();
        Stack<String> semicolonStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (String s : script.split("")) {
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
                case "\n":
                case ";":
                    if (semicolonStack.isEmpty()) {
                        result.add(sb.toString());
                        sb = new StringBuilder();
                        continue;
                    }
                    break;

            }
            sb.append(s);
        }
        return result;
    }
}
