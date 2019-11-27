package com.cjh.expression.ctx;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * @author JianhuiChen
 * @description 脚本运行上下文
 * @date 2019-11-25
 */
@Setter
@Getter
public class RunningContext extends HashMap<String, Object> {

    /**
     * 脚本是否已经执行完成
     */
    private boolean complete = false;

    /**
     * 脚本执行结果
     */
    private Object result = null;
}
