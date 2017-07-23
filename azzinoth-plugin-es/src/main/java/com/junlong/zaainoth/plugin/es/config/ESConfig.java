package com.junlong.zaainoth.plugin.es.config;

import com.junlong.zaainoth.plugin.es.domain.ESService;

import java.util.List;

/**
 * Created by niujunlong on 2017/7/22.
 */
public class ESConfig {
    private String clusterName;
    private List<ESService> serviceList;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public List<ESService> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ESService> serviceList) {
        this.serviceList = serviceList;
    }
}
