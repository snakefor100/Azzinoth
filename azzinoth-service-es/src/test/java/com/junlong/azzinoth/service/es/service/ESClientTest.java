package com.junlong.azzinoth.service.es.service;

import com.junlong.azzinoth.common.constants.CommonConstants;
import com.junlong.azzinoth.common.domain.MethodEntity;
import com.junlong.azzinoth.service.es.domain.ESServiceConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by niujunlong on 2017/7/27.
 */
public class ESClientTest {
    public static ESClient es;


    @Before
    public void init() {
        ESServiceConfig config = new ESServiceConfig();
        config.setServerList("localhost:9300");
        config.setClusterName("myindex");
        config.setTransportSniff(true);

        es = new ESClient(config);

    }

    @Test
    public void createIndex() {
        MethodEntity methodEntity = new MethodEntity();
        methodEntity.setId(UUID.randomUUID().toString());
        methodEntity.setStartTime(System.currentTimeMillis());
        methodEntity.setErrorCode(CommonConstants.ERROR_CODE_SUCCESS);
        es.saveMethodIndex("azzinoth", "test", methodEntity);
    }

    @Test
    public void updateIndex() {
        MethodEntity methodEntity = new MethodEntity();
        methodEntity.setId("7c537e9a-fa42-4760-ac0d-e6415b3505d4");
        methodEntity.setStartTime(1501174498345L);
        methodEntity.setErrorCode(CommonConstants.ERROR_CODE_FAIL);
        methodEntity.setEndTime(1502168685699L);
        methodEntity.setConsumeTime(1502168685699L - 1501174498345L);
        es.updateMethodIndex("azzinoth", "test", "73617733-3bd4-4d2c-994c-1f9c353ac376", methodEntity);
    }

    @Test
    public void searchIndex() {
//        es.queryErrorMethodDetail("azzinoth","test",1500168685669L,1511168685669L);
        int n = 10;
        int floor = (int) (10 * 0.99);
        System.out.println(floor);
    }

}
