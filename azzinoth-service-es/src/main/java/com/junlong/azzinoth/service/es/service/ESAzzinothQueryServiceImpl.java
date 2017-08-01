package com.junlong.azzinoth.service.es.service;

import com.junlong.azzinoth.common.constants.CommonConstants;
import com.junlong.azzinoth.common.domain.MethodResultEntity;
import com.junlong.azzinoth.common.service.AzzinothQueryService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by niujunlong on 2017/8/2.
 */
@Service
public class ESAzzinothQueryServiceImpl implements AzzinothQueryService {
    @Resource
    private ESClient esClient;

    @Override
    public MethodResultEntity queryMethodResultInTime(String startTime, String endTime) {
        long start = DateTime.parse(startTime, DateTimeFormat.forPattern(CommonConstants.DEFAULT_TIME_FORMAT)).toDate().getTime();
        long end = DateTime.parse(endTime, DateTimeFormat.forPattern(CommonConstants.DEFAULT_TIME_FORMAT)).toDate().getTime();
        return null;
    }
}
