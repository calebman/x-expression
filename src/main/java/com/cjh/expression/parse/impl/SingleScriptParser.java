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
        StringBuilder sb = new StringBuilder();
        boolean doubleQuotesEnd = true;
        boolean singleQuotesEnd = true;
        for (char c : script.toCharArray()) {
            switch (c) {
                case '"':
                    doubleQuotesEnd = !doubleQuotesEnd;
                    break;
                case '\'':
                    singleQuotesEnd = !singleQuotesEnd;
                    break;
                case '\n':
                case ';':
                    if (doubleQuotesEnd && singleQuotesEnd) {
                        String line = sb.toString().trim();
                        if (!line.isEmpty()) {
                            result.add(sb.toString());
                        }
                        sb = new StringBuilder();
                        continue;
                    }
                    break;

            }
            sb.append(c);
        }
        String line = sb.toString().trim();
        if (!line.isEmpty()) {
            result.add(sb.toString());
        }
        return result;
    }
}
