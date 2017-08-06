package com.junlong.azzinoth.common.service;

import com.junlong.azzinoth.common.domain.MethodResultEntity;

/**
 * 查询接口，主要负责查询方法TP耗时等
 * Created by niujunlong on 2017/8/2.
 */
public interface AzzinothQueryService {
    /**
     * 查询时间段内方法的监控结果
     * @param appName
     * @param methodName
     * @param startTime
     * @param endTime
     * @return
     */
    MethodResultEntity queryMethodResultInTime(String appName, String methodName, String startTime, String endTime);
}
