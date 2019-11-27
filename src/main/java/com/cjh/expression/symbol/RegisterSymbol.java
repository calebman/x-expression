package com.cjh.expression.symbol;

import com.cjh.expression.symbol.impl.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JianhuiChen
 * @description 内部注册的运算符表达式
 * @date 2019-11-25
 */
@RequiredArgsConstructor
@Getter
public enum RegisterSymbol {
    MUL(new char[]{'*'}, 10, MulSymbolExpression.class),
    DIV(new char[]{'/'}, 10, DivSymbolExpression.class),
    POW(new char[]{'^'}, 10, PowSymbolExpression.class),
    ADD(new char[]{'+'}, 8, AddSymbolExpression.class),
    SUB(new char[]{'-'}, 8, SubSymbolExpression.class),
    LTEQ(new char[]{'<', '='}, 6, LTEQSymbolExpression.class),
    GTEQ(new char[]{'>', '='}, 6, GTEQSymbolExpression.class),
    LT(new char[]{'<'}, 6, LTSymbolExpression.class),
    GT(new char[]{'>'}, 6, GTSymbolExpression.class),
    AND(new char[]{'&', '&'}, 4, AndSymbolExpression.class),
    OR(new char[]{'|', '|'}, 4, OrSymbolExpression.class),
    ASSI(new char[]{'='}, 0, AssiSymbolExpression.class);

    /**
     * 符号
     */
    private final char[] symbol;

    /**
     * 优先级
     */
    private final int priority;

    /**
     * 执行器
     */
    private final Class<? extends SymbolExpression> expression;

    public static List<String> symbols() {
        List<String> symbols = new ArrayList<>();
        for (RegisterSymbol value : values()) {
            symbols.add(String.valueOf(value.getSymbol()));
        }
        return symbols;
    }

    public static List<char[]> symbolChars() {
        List<char[]> symbols = new ArrayList<>();
        for (RegisterSymbol value : values()) {
            symbols.add(value.getSymbol());
        }
        return symbols;
    }

    public static boolean isRegisterSymbol(String symbol) {
        for (RegisterSymbol value : values()) {
            if (isSameSymbol(value.getSymbol(), symbol)) {
                return true;
            }
        }
        return false;
    }

    public static RegisterSymbol findRegisterSymbol(String symbol) {
        for (RegisterSymbol value : values()) {
            if (isSameSymbol(value.getSymbol(), symbol)) {
                return value;
            }
        }
        return null;
    }

    private static boolean isSameSymbol(char[] symbolChar, String str) {
        char[] strChar = str.toCharArray();
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
