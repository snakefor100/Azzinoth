package com.junlong.azzinoth.barrier.annotation;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * AProfiler注解，可替换RequestMapping注解
 * Created by niujunlong on 2017/7/23.
 */
@RequestMapping
@AProfiler(appName = "UNKNOW",methodName = "UNKNOW")
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AControllerProfiler {
}
