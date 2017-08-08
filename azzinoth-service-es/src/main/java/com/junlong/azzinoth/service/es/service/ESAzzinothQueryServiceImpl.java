package com.junlong.azzinoth.service.es.service;

import com.junlong.azzinoth.common.constants.CommonConstants;
import com.junlong.azzinoth.common.domain.MethodResultEntity;
import com.junlong.azzinoth.common.service.AzzinothQueryService;
import com.junlong.azzinoth.service.es.constants.EsConstants;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * Created by niujunlong on 2017/8/2.
 */

public class ESAzzinothQueryServiceImpl implements AzzinothQueryService {
    private final static DecimalFormat doubleFormat = new DecimalFormat("#.00");
    @Resource
    private ESClient esClient;

    @Override
    public MethodResultEntity<SearchHit> queryMethodResultInTime(String appName, String methodName,String startTime, String endTime) {
        long start = DateTime.parse(startTime, DateTimeFormat.forPattern(CommonConstants.DEFAULT_TIME_FORMAT)).toDate().getTime();
        long end = DateTime.parse(endTime, DateTimeFormat.forPattern(CommonConstants.DEFAULT_TIME_FORMAT)).toDate().getTime();
        return queryMethodResultInTime(appName,methodName,start,end);
    }

    @Override
    public MethodResultEntity queryMethodResultInTime(String appName, String methodName, Long startTime, Long endTime) {
        MethodResultEntity resultEntity = new MethodResultEntity();
        SearchResponse totalSearchResponse = esClient.queryMethodDetail(appName, methodName, startTime, endTime);
        SearchHit[] totalHitArr = totalSearchResponse.getHits().getHits();
        SearchResponse errorSearchResponse = esClient.queryErrorMethodDetail(appName, methodName, startTime, endTime);
        //请求总数
        long totalHits = totalSearchResponse.getHits().getTotalHits();
        long errorHits = errorSearchResponse.getHits().getTotalHits();

        resultEntity.setRequestCount(totalHits);
        resultEntity.setTP99(Long.valueOf(totalHitArr[(int) (0.99 * totalHits)].getSource().get(EsConstants.DOC_FIELD_CONSUME_TIME).toString()));
        resultEntity.setTP90(Long.valueOf(totalHitArr[(int) (0.9 * totalHits)].getSource().get(EsConstants.DOC_FIELD_CONSUME_TIME).toString()));
        resultEntity.setTP80(Long.valueOf(totalHitArr[(int) (0.8 * totalHits)].getSource().get(EsConstants.DOC_FIELD_CONSUME_TIME).toString()));
        resultEntity.setTP70(Long.valueOf(totalHitArr[(int) (0.7 * totalHits)].getSource().get(EsConstants.DOC_FIELD_CONSUME_TIME).toString()));
        resultEntity.setTP60(Long.valueOf(totalHitArr[(int) (0.6 * totalHits)].getSource().get(EsConstants.DOC_FIELD_CONSUME_TIME).toString()));
        resultEntity.setTP50(Long.valueOf(totalHitArr[(int) (0.5 * totalHits)].getSource().get(EsConstants.DOC_FIELD_CONSUME_TIME).toString()));
        resultEntity.setSuccessRate(1 -  Double.valueOf(doubleFormat.format(errorHits/totalHits)));
        resultEntity.setErrDetail(Arrays.asList(errorSearchResponse.getHits().getHits()));
        return resultEntity;
    }
}
