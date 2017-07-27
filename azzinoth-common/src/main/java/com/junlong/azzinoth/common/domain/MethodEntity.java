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
     * 消耗时间
     */
    private Long consumeTime;
    /**
     * 是否成功
     */
    private Integer errorCode;
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 方法开始时间
     */
    private Long startTime;
    /**
     * 方法结束时间
     */
    private Long endTime;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
