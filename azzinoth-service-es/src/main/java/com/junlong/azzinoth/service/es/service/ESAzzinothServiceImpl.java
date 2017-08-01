package com.junlong.azzinoth.service.es.service;

import com.junlong.azzinoth.common.domain.MethodEntity;
import com.junlong.azzinoth.common.enums.ACustomFunctionEnum;
import com.junlong.azzinoth.common.service.AzzinothService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public MethodEntity registerMethodStart(String appName, String methodName,MethodEntity methodEntity) {
        esClient.saveMethodIndex(appName, methodName, methodEntity);
        return methodEntity;
    }

    @Override
    public MethodEntity registerMethodEnd(String appName, String methodName,String id, MethodEntity methodEntity) {

        esClient.updateMethodIndex(appName, methodName,id, methodEntity);
        return methodEntity;
    }

    @Override
    public MethodEntity registerMethodError(String appName, String methodName,String id, MethodEntity methodEntity, Throwable throwable) {
        esClient.updateMethodIndex(appName,methodName,id,methodEntity);
        return methodEntity;
    }
}
