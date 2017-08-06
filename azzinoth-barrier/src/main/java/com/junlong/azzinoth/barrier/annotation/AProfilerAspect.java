package com.junlong.azzinoth.barrier.annotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junlong.azzinoth.barrier.exception.MethodTimeOutException;
import com.junlong.azzinoth.common.constants.ErrorConstants;
import com.junlong.azzinoth.common.domain.MethodEntity;
import com.junlong.azzinoth.common.enums.AProEnum;
import com.junlong.azzinoth.common.service.AzzinothService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

import static com.junlong.azzinoth.common.constants.ErrorConstants.APROENUM_LOG;

/**
 * AProfiler注解服务类
 * Created by niujunlong on 2017/7/22.
 */
@Aspect
@Component
public class AProfilerAspect implements InitializingBean {
    private final static Logger LOG = LoggerFactory.getLogger(AProfilerAspect.class);
    @Resource(name = "azzinothService")
    private AzzinothService service;
    private static final ObjectMapper OBJECT_MAPPER =  new ObjectMapper();

    @Pointcut("@annotation(com.junlong.azzinoth.barrier.annotation.AProfiler)")
    public void AProfilerPoint() {
    }


    /**
     * AProfiler注解方法切面
     *
     * @return
     */
    @Around("AProfilerPoint()")
    public Object execHandler(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = null;
        MethodEntity methodEntity = null;
        long endTime = 0L;
        Object[] param = null;
        AProfiler aProfiler = null;
        try {
            Method method = getMethod(joinPoint);
            param = joinPoint.getArgs();
            aProfiler = method.getAnnotation(AProfiler.class);
            methodEntity = service.registerMethodStart(aProfiler.appName(), aProfiler.methodName(),startTime);
            result = joinPoint.proceed();
            endTime = System.currentTimeMillis();
            long consumeTime = startTime - endTime;

            for(AProEnum proEnum : aProfiler.mstate()){
                switch (proEnum){
                    case TIMEOUT:
                        if(consumeTime > aProfiler.maxTime()){
                            throw new MethodTimeOutException(ErrorConstants.Error.METHOD_TIME_OUT);
                        }
                    case LOG:
                        LOG.info(APROENUM_LOG,aProfiler.methodName(),OBJECT_MAPPER.writeValueAsString(param),OBJECT_MAPPER.writeValueAsString(result));
                }
            }
        }catch (Throwable e){
            service.registerMethodError(aProfiler.appName(),aProfiler.methodName(),methodEntity,e);
            throw e;
        }finally {
            endTime = System.currentTimeMillis();
            service.registerMethodEnd(aProfiler.appName(),aProfiler.methodName(),methodEntity,endTime);
        }
        return result;
    }

    private Method getMethod(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }



    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
