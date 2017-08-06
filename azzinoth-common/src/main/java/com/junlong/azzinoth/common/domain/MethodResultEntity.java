package com.junlong.azzinoth.common.domain;

import java.util.List;

/**
 * 方法监控结果（某个时间段内，方法的TP值，错误率等）
 * Created by niujunlong on 2017/8/2.
 */
public class MethodResultEntity<T> {
    private Long TP99;
    private Long TP90;
    private Long TP80;
    private Long TP70;
    private Long TP60;
    private Long TP50;
    private Double successRate;
    private Long requestCount;
    private List<T> errDetail;

    public List<T> getErrDetail() {
        return errDetail;
    }

    public void setErrDetail(List<T> errDetail) {
        this.errDetail = errDetail;
    }

    public Long getTP99() {
        return TP99;
    }

    public void setTP99(Long TP99) {
        this.TP99 = TP99;
    }

    public Long getTP90() {
        return TP90;
    }

    public void setTP90(Long TP90) {
        this.TP90 = TP90;
    }

    public Long getTP80() {
        return TP80;
    }

    public void setTP80(Long TP80) {
        this.TP80 = TP80;
    }

    public Long getTP70() {
        return TP70;
    }

    public void setTP70(Long TP70) {
        this.TP70 = TP70;
    }

    public Long getTP60() {
        return TP60;
    }

    public void setTP60(Long TP60) {
        this.TP60 = TP60;
    }

    public Long getTP50() {
        return TP50;
    }

    public void setTP50(Long TP50) {
        this.TP50 = TP50;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }
}
