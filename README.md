<p align="center"><img src="https://resources.chenjianhui.site/x-expression.png"/></p>
<p align="center"><strong>基于Java的简单脚本解析库</strong></p>

## 简介

**x-expression**是一个基于Java实现的带有运算/双目/函数表达式解析功能的代码库，一般用于动态规则配置的场景，如：

- 优惠券价格配置
- 特定场景的指标得分计算

运算符与函数可支持扩展，现内置的运算符与函数如下

- 运算符
----
名称 | 含义
---- | ----
+|加法
-|减法
*|乘法
/|除法
^|幂
&gt;=|大于等于
&lt;=|小于等于
&gt;|大于
&lt;|小雨
&amp;&amp;|与
&#124;&#124;|或


- 函数
----
名称 | 含义
---- | ----
max|求最大值
min|求最小值
sum|累加
print|输出所有参数
return|终止脚本执行并返回结果

## 如何使用

```java
public class TestMain {

    public static void main(String[] args) {
        String script = "c = a + b * c - 2 ^ a + b / 2;return(c);";
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
```
**系统控制台将输出12.0**

## 开发计划

- [x] 支持基本运算符
- [x] 支持函数
- [x] 支持双目运算符
- [ ] 支持自定义运算符
- [ ] 支持自定义函数
- [ ] 支持Maven引入
