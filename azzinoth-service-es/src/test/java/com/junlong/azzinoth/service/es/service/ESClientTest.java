package com.junlong.azzinoth.service.es.service;

import com.junlong.azzinoth.common.domain.CommonConstants;
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
    public static final String ES_CONFIG_CLUSTER_NAME = "cluster.name";
    public static final String ES_CONFIG_SNIFF = "client.transport.sniff";
    @Before
    public void init() {
        ESServiceConfig config = new ESServiceConfig();
        config.setServerList(new ArrayList<String>() {
            {
                add("localhost:9300");
            }
        });
        Map<String, String> map = new HashMap<String, String>();
        map.put(ES_CONFIG_CLUSTER_NAME, "myindex");
        map.put(ES_CONFIG_SNIFF, "true");
        config.setSetting(map);
        es = new ESClient();
        es.init(config);
    }

    @Test
    public void createIndex() {
        MethodEntity methodEntity = new MethodEntity();
        methodEntity.setId(UUID.randomUUID().toString());
        methodEntity.setStartTime(System.currentTimeMillis());
        methodEntity.setErrorCode(CommonConstants.ERROR_CODE_SUCCESS);
        es.saveMethodIndex("azzinoth","test",methodEntity);
    }
}
