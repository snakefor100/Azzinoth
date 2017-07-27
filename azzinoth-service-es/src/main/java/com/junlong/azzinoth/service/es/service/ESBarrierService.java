package com.junlong.azzinoth.service.es.service;

import com.junlong.azzinoth.common.domain.ACustomFunctionEnum;
import com.junlong.azzinoth.common.domain.MethodEntity;
import com.junlong.azzinoth.common.service.ABarrierService;
import com.junlong.azzinoth.service.es.constants.EsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by niujunlong on 2017/7/24.
 */
@Service
public class ESBarrierService implements ABarrierService {
    private final static Logger LOG = LoggerFactory.getLogger(ESBarrierService.class);

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
    public MethodEntity registerMethodError(String appName, String methodName, MethodEntity methodEntity, Throwable throwable) {
        appName = appName + EsConstants.INDEX_ERROR_SUFFIX;
        esClient.saveMethodIndex(appName, methodName, methodEntity);
        return methodEntity;
    }
}
