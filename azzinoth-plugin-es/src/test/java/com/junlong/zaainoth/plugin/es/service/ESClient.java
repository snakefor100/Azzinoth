package com.junlong.zaainoth.plugin.es.service;

import com.junlong.zaainoth.plugin.es.config.ESConfig;
import com.junlong.zaainoth.plugin.es.constants.AppConstants;
import com.junlong.zaainoth.plugin.es.domain.ESService;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niujunlong on 17/7/21.
 */
public class ESClient {

    public static TransportClient client;

    @Before
    public void init() {
        ESConfig esConfig = new ESConfig();
        esConfig.setClusterName(" my-application");
        esConfig.setServiceList(new ArrayList<ESService>() {
            {
                add(new ESService("127.0.0.1", 9200));
            }
        });
        try {
            Settings build = Settings.builder().put(AppConstants.CLUSTER_NAME, esConfig.getClusterName()).build();
            PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(build);
            List<InetSocketTransportAddress> addressList = new ArrayList<InetSocketTransportAddress>(esConfig.getServiceList().size());
            for (ESService service : esConfig.getServiceList()) {
                addressList.add((new InetSocketTransportAddress(InetAddress.getByName(service.getIp()), 9300)));
            }
            client = preBuiltTransportClient.addTransportAddresses(addressList.toArray(new InetSocketTransportAddress[esConfig.getServiceList().size()]));
            client.prepareIndex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void createEmployee(){

    }

}
