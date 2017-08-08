package com.junlong.azzinoth.service;

import com.junlong.azzinoth.barrier.annotation.AProfiler;
import com.junlong.azzinoth.common.enums.ACustomFunctionEnum;
import com.junlong.azzinoth.common.enums.AProEnum;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by niujunlong on 2017/8/6.
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    @AProfiler(appName = "azzinoth",methodName = "testAnnotation",maxTime = 1000,mstate = {AProEnum.TIMEOUT,AProEnum.LOG},function = {ACustomFunctionEnum.TP,ACustomFunctionEnum.FUNCTIONERROR})
    public void add() {
        try{
            int consume = new Random().nextInt(1500);
            System.out.println("AAA");
            Thread.sleep(consume);
        }catch (Exception e){

        }

    }
}
