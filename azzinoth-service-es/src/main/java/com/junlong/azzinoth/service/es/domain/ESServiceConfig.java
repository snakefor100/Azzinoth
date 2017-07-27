package com.junlong.azzinoth.service.es.domain;

import java.util.List;
import java.util.Map;

/**
 * Created by niujunlong on 2017/7/26.
 */
public class ESServiceConfig {
    private Map<String,String> setting;
    private List<String> serverList;

    public Map<String, String> getSetting() {
        return setting;
    }

    public void setSetting(Map<String, String> setting) {
        this.setting = setting;
    }

    public List<String> getServerList() {
        return serverList;
    }

    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }
}
