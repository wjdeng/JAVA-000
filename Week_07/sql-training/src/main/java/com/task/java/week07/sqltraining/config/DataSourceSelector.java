package com.task.java.week07.sqltraining.config;

import java.lang.annotation.*;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:27
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DataSourceSelector {

	DynamicDataSourceEnum value() default DynamicDataSourceEnum.MASTER;
	boolean clear() default true;
}
