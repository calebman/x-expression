package com.cjh.expression.parser;

import com.cjh.expression.BaseTest;
import com.cjh.expression.parse.Parser;
import com.cjh.expression.parse.impl.SingleScriptParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author JianhuiChen
 * @description 脚本切割解析器测试
 * @date 2019-11-29
 */
public class SingleScriptParserTest extends BaseTest {

    private final Parser<List<String>> parser = new SingleScriptParser();

    @Test
    public void normal() {
        String[] actuals = new String[]{"a = a + b", "return(a)"};
        check("a = a + b;return(a);", actuals);
        check("a = a + b\nreturn(a)", actuals);
        check("a = a + b;return(a)", actuals);
        check(";;;\n\na = a + b;\n;;return(a)", actuals);
    }

    @Test
    public void complex() {
        String[] actuals = new String[]{"a = 'axs &*^$%#$&*||;\n\n1211' + 'axsa(*)&^'", "return(a)"};
        check("a = 'axs &*^$%#$&*||;\n\n1211' + 'axsa(*)&^';return(a);", actuals);
    }

    /**
     * 检测结果
     *
     * @param script  测试脚本
     * @param actuals 目标结果
     */
    private void check(String script, String... actuals) {
        List<String> lines = parser.parse(script);
        String result = String.join("、", lines);
        Assert.assertArrayEquals(String.format("\n---脚本---\n%s\n---拆分错误，错误结果为---\n%s\n", script, result), lines.toArray(new String[]{}), actuals);
    }
}
