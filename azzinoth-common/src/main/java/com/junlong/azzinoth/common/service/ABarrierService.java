package com.junlong.azzinoth.common.service;

import com.junlong.azzinoth.common.domain.ACustomFunctionEnum;
import com.junlong.azzinoth.common.domain.MethodEntity;

/**
 * barrier服务类
 * Created by niujunlong on 2017/7/23.
 */
public interface ABarrierService {
    /**
     * 向监控中心注册方法信息
     * @param appName
     * @param methodName
     * @param customFunctionEnum
     */
    public void registerMethodInfo(String appName, String methodName, ACustomFunctionEnum ... customFunctionEnum);

    /**
     * 方法开始
     * @param appName
     * @param methodName
     * @return
     */
    public MethodEntity registerMethodStart(String appName, String methodName);

    /**
     * 方法结束
     * @param methodEntity
     * @return
     */
    public MethodEntity registerMethodEnd(MethodEntity methodEntity);

    /**
     * 方法异常注册
     * @param methodEntity
     * @param throwable
     * @return
     */
    public MethodEntity registerMethodError(MethodEntity methodEntity, Throwable throwable);
}
