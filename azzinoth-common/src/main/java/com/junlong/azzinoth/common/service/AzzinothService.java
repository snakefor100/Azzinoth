package com.junlong.azzinoth.common.service;

import com.junlong.azzinoth.common.domain.MethodEntity;
import com.junlong.azzinoth.common.enums.ACustomFunctionEnum;

/**
 * 服务类接口，主要负责MethodEntity的持久化
 * Created by niujunlong on 2017/7/23.
 */
public interface AzzinothService {
    /**
     * 向监控中心注册方法信息
     * @param appName
     * @param methodName
     * @param customFunctionEnum
     */
    public void registerMethodInfo(String appName, String methodName, ACustomFunctionEnum... customFunctionEnum);

    /**
     * 方法开始
     * @param appName
     * @param methodName
     * @return
     */
    public MethodEntity registerMethodStart(String appName, String methodName, MethodEntity methodEntity);

    /**
     * 方法结束
     * @param methodEntity
     * @return
     */
    public MethodEntity registerMethodEnd(String appName, String methodName, String id, MethodEntity methodEntity);

    /**
     * 方法异常注册
     * @param methodEntity
     * @param throwable
     * @return
     */
    public MethodEntity registerMethodError(String appName, String methodName, String id, MethodEntity methodEntity, Throwable throwable);
}
