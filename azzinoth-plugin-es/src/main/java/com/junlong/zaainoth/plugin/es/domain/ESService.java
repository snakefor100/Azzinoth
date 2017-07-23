package com.junlong.zaainoth.plugin.es.domain;

/**
 * Created by niujunlong on 2017/7/22.
 */
public class ESService {
    private String ip;
    private Integer port;

    public ESService(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
