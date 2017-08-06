package com.junlong.azzinoth.web.controller;

import com.junlong.azzinoth.barrier.annotation.AProfiler;
import com.junlong.azzinoth.common.domain.MethodResultEntity;
import com.junlong.azzinoth.common.enums.ACustomFunctionEnum;
import com.junlong.azzinoth.common.enums.AProEnum;
import com.junlong.azzinoth.common.service.AzzinothQueryService;
import com.junlong.azzinoth.service.TestService;
import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;


/**
 * Created by niujunlong on 2017/8/3.
 */
@Controller
@RequestMapping("/azzinoth")
public class AzzinothController {
    @Resource(name = "azzinothQueryService")
    private AzzinothQueryService azzinothQueryService;
    @Resource
    public TestService testService;

    @RequestMapping("/queryMethodPerformance")
    public MethodResultEntity<SearchHit> monitorMethod(String appName,String method,String startTime,String endTime){
        return azzinothQueryService.queryMethodResultInTime(appName,method,startTime,endTime);
    }

    @RequestMapping("/testAnnotation")
    @ResponseBody
    public String testAnnotation(){
        int consume = new Random().nextInt(1500);
        try {
            testService.add();
            Thread.sleep(consume);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "FAILUS";
        }
        return "SUCCESS";
    }
}
