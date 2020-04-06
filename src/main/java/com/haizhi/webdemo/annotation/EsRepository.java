package com.haizhi.webdemo.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Es持久层注解
 * 1.接口或者方法上可用
 * 2.若在xml有对应的实现，使用xml中的sql实现对应的方法；若无，则使用impl实现类中的方法
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Repository
public @interface EsRepository {

    boolean value() default false;

}
