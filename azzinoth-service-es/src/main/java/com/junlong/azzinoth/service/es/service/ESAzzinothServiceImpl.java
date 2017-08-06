package com.junlong.azzinoth.service.es.service;

import com.junlong.azzinoth.common.constants.CommonConstants;
import com.junlong.azzinoth.common.domain.MethodEntity;
import com.junlong.azzinoth.common.enums.ACustomFunctionEnum;
import com.junlong.azzinoth.common.service.AzzinothService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by niujunlong on 2017/7/24.
 */
@Service
public class ESAzzinothServiceImpl implements AzzinothService {
    private final static Logger LOG = LoggerFactory.getLogger(ESAzzinothServiceImpl.class);
    @Resource
    private ESClient esClient;

    @Override
    public void registerMethodInfo(String appName, String methodName, ACustomFunctionEnum... customFunctionEnum) {

    }

    @Override
    public MethodEntity registerMethodStart(String appName, String methodName,Long time) {
        MethodEntity methodEntity = new MethodEntity();
        methodEntity.setId(UUID.randomUUID().toString());
        methodEntity.setStartTime(time);
        methodEntity.setErrorCode(CommonConstants.ERROR_CODE_SUCCESS);
        esClient.saveMethodIndex(appName, methodName, methodEntity);
        return methodEntity;
    }

    @Override
    public MethodEntity registerMethodEnd(String appName, String methodName,MethodEntity methodEntity,Long time) {
        methodEntity.setEndTime(time);
        methodEntity.setConsumeTime(methodEntity.getEndTime() - methodEntity.getStartTime());
        esClient.updateMethodIndex(appName, methodName,methodEntity.getId(), methodEntity);
        return methodEntity;
    }

    @Override
    public MethodEntity registerMethodError(String appName, String methodName, MethodEntity methodEntity, Throwable throwable) {
        methodEntity.setErrorCode(CommonConstants.ERROR_CODE_FAIL);
        methodEntity.setErrorMsg(throwable.getMessage());
        esClient.updateMethodIndex(appName,methodName,methodEntity.getId(),methodEntity);
        return methodEntity;
    }
}
