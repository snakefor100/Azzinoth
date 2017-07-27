package com.junlong.azzinoth.common.service;

import com.junlong.azzinoth.common.domain.ACustomFunctionEnum;
import com.junlong.azzinoth.common.domain.CommonConstants;
import com.junlong.azzinoth.common.domain.MethodEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


/**
 * Created by niujunlong on 2017/7/25.
 */
public class AzzinothProfiler {
    private final static Logger LOG = LoggerFactory.getLogger(AzzinothProfiler.class);
    private static ABarrierService service;
    public AzzinothProfiler(ABarrierService service){
        this.service = service;
    }

    /**
     * 向监控中心注册方法信息
     * @param appName
     * @param methodName
     * @param customFunctionEnum
     */
    public static void registerMethodInfo(String appName, String methodName, ACustomFunctionEnum... customFunctionEnum){
        service.registerMethodInfo(appName,methodName,customFunctionEnum);
    }

    /**
     * 方法开始
     * @param appName
     * @param methodName
     * @return
     */
    public static MethodEntity registerMethodStart(String appName, String methodName, Long time){
        MethodEntity methodEntity = null;
        try {
            methodEntity = new MethodEntity();
            methodEntity.setId(UUID.randomUUID().toString());
            methodEntity.setStartTime(time);
            methodEntity.setErrorCode(CommonConstants.ERROR_CODE_SUCCESS);
            service.registerMethodStart(appName,methodName,methodEntity);
        }catch (Exception e){
            LOG.error("",e);
        }
        return methodEntity;
    }

    /**
     * 方法结束
     * @param methodEntity
     * @return
     */
    public static MethodEntity registerMethodEnd(String appName, String methodName,MethodEntity methodEntity, Long time){
        try {
            methodEntity.setEndTime(time);
            methodEntity.setConsumeTime(methodEntity.getEndTime() - methodEntity.getStartTime());
            service.registerMethodEnd(appName,methodName,methodEntity.getId(),methodEntity);
        }catch (Exception e){
            LOG.error("",e);
        }
        return methodEntity;
    }

    /**
     * 方法异常注册
     * @param methodEntity
     * @param throwable
     * @return
     */
    public static MethodEntity registerMethodError(String appName, String methodName,MethodEntity methodEntity, Throwable throwable){
        try {
            methodEntity.setErrorCode(CommonConstants.ERROR_CODE_FAIL);
            methodEntity.setErrorMsg(throwable.getMessage());
            methodEntity.setConsumeTime(methodEntity.getEndTime() - methodEntity.getStartTime());
            service.registerMethodError(appName,methodName,methodEntity,throwable);
        }catch (Exception e){
            LOG.error("",e);
        }
        return methodEntity;
    }
}
