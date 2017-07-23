package com.junlong.azzinoth.common.domain;

/**
 * 方法实体
 * Created by niujunlong on 2017/7/22.
 */
public class MethodEntity {
    /**
     * 标识请求方法的id，每次请求id不一样
     */
    private String id;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 消耗时间
     */
    private Long consumeTime;
    /**
     * 是否成功
     */
    private Boolean errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
}
