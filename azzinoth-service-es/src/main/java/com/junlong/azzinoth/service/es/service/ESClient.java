package com.junlong.azzinoth.service.es.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junlong.azzinoth.common.domain.CommonConstants;
import com.junlong.azzinoth.common.domain.MethodEntity;
import com.junlong.azzinoth.service.es.constants.EsConstants;
import com.junlong.azzinoth.service.es.domain.ESServiceConfig;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

/**
 * elastic search 客户端
 * Created by niujunlong on 2017/7/25.
 */
@Service
public class ESClient {
    private final static Logger LOG = LoggerFactory.getLogger(ESBarrierService.class);
    private static final ObjectMapper OBJECT_MAPPER =  new ObjectMapper();
    private TransportClient client;


    public void saveMethodIndex(String indexName, String type, MethodEntity methodEntity){
        try {
            IndexResponse indexResponse = client.prepareIndex(indexName, type).setId(methodEntity.getId()).setSource(OBJECT_MAPPER.writeValueAsString(methodEntity)).get();
            if(indexResponse.status() != RestStatus.CREATED){
                LOG.error("");
            }
        }catch (Exception e){
            LOG.error("", e);
        }

    }

    public void updateMethodIndex(String indexName, String type,String id, MethodEntity methodEntity){
        try {
            UpdateResponse updateResponse = client.prepareUpdate(indexName, type,id).setDoc(XContentFactory.jsonBuilder()
                    .startObject()
                    .field(EsConstants.DOC_FIELD_START_TIME, methodEntity.getStartTime())
                    .field(EsConstants.DOC_FIELD_END_TIME, methodEntity.getEndTime())
                    .field(EsConstants.DOC_FIELD_ERROR_CODE, methodEntity.getErrorCode())
                    .field(EsConstants.DOC_FIELD_ERROR_MSG, methodEntity.getErrorMsg())
                    .field(EsConstants.DOC_FIELD_CONSUME_TIME,methodEntity.getConsumeTime())
                    .endObject()).get();
        }catch (Exception e){
            LOG.error("", e);
        }
    }

/**********************************      初始化/关闭     ************************************************************************/
    /**
     * 初始化ES客戶端
     *
     * @param config
     */
    @PostConstruct
    public void init(ESServiceConfig config) {
        try {
            Map<String, String> configMap = config.getSetting();
            List<String> serverList = config.getServerList();

            Settings settings = Settings.builder().put(configMap).build();
            PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings);
            TransportAddress[] transportAddressArr = new TransportAddress[serverList.size()];
            for (int i = 0; i < serverList.size(); i++) {
                String[] split = serverList.get(i).split(CommonConstants.SYMBOL_COLON);
                transportAddressArr[i] = new InetSocketTransportAddress(InetAddress.getByName(split[0]), Integer.valueOf(split[1]));
            }
            client = preBuiltTransportClient.addTransportAddresses(transportAddressArr);
        } catch (Exception e) {
            LOG.error("", e);
        }
    }

    /**
     * 关闭ES客户端
     */
    @PreDestroy
    public void close() {
        client.close();
    }
}
