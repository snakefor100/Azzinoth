package com.junlong.azzinoth.service.es.constants;

import org.springframework.beans.factory.annotation.Value;

/**
 * 静态常量
 * Created by niujunlong on 2017/7/26.
 */
public class EsConstants {

    /*******************************************  elastic search 索引字段 *******************************************************************/
    public static final String INDEX_ERROR_SUFFIX="_error";
    public static final String DOC_FIELD_CONSUME_TIME="consumeTime";
    public static final String DOC_FIELD_ERROR_CODE="errorCode";
    public static final String DOC_FIELD_ERROR_MSG="errorMsg";
    public static final String DOC_FIELD_START_TIME="startTime";
    public static final String DOC_FIELD_END_TIME="endTime";

    /*******************************************  elastic search 配置字段 *******************************************************************/
    public static final String ES_CONFIG_CLUSTER_NAME = "cluster.name";
    public static final String ES_CONFIG_SNIFF = "client.transport.sniff";

}
