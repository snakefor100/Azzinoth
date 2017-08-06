package com.junlong.azzinoth.service.es.domain;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by niujunlong on 2017/7/26.
 */
public class ESServiceConfig {

    private String serverList;

    private String clusterName;

    private Boolean transportSniff;

    public String getServerList() {
        return serverList;
    }

    public void setServerList(String serverList) {
        this.serverList = serverList;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Boolean getTransportSniff() {
        return transportSniff;
    }

    public void setTransportSniff(Boolean transportSniff) {
        this.transportSniff = transportSniff;
    }
}
