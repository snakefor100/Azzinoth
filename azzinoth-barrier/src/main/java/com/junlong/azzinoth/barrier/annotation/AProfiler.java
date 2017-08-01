package com.junlong.azzinoth.barrier.annotation;


import com.junlong.azzinoth.common.enums.ACustomFunctionEnum;
import com.junlong.azzinoth.common.enums.AProEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义监控注解
 * Created by niujunlong on 2017/7/22.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface AProfiler {
    /**
     * 项目名称
     * @return
     */
    String appName();

    /**
     * 监控方法名(保证唯一)
     * @return
     */
    String methodName();

    /**
     * 默认方法超时时间
     * @return
     */
    long maxTime() default 10L;

    /**
     * 监控方法功能 默认：日志功能：开启  超时检查： 关闭
     * @return
     */
    AProEnum[] mstate() default {AProEnum.LOG};

    /**
     * 监控功能注册 默认： 方法TP监控：开启  方法错误率监控：开启 心跳检测:开启
     * @return
     */
    ACustomFunctionEnum[] function() default {ACustomFunctionEnum.TP,ACustomFunctionEnum.FUNCTIONERROR,ACustomFunctionEnum.HEARTBEAT};
    /**
     *
     * @return
     */
    boolean heartBeat() default false;

    boolean functionError() default true;
}
