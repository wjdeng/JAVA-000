package com.task.java.week07.sqltraining.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author Wayne.Deng
 * @Date 2020/12/2 下午7:27
 * @Version 1.0
 **/
@Slf4j
@Aspect
@Order(value = 1)
@Component
public class DataSourceContextAop {
	//写自定义注解的包路径
	@Around("@annotation(com.task.java.week07.sqltraining.config.DataSourceSelector)")
	public Object setDynamicDataSource(ProceedingJoinPoint pjp) throws Throwable {
		boolean clear = true;
		try {
			Method method = this.getMethod(pjp);
			DataSourceSelector dataSourceImport = method.getAnnotation(DataSourceSelector.class);
			clear = dataSourceImport.clear();
			DataSourceContextHolder.set(dataSourceImport.value().getDataSourceName());
			log.info("========数据源切换至：{}", dataSourceImport.value().getDataSourceName());
			return pjp.proceed();
		} finally {
			if (clear) {
				DataSourceContextHolder.clear();
			}
		}
	}
	private Method getMethod(JoinPoint pjp) {
		MethodSignature signature = (MethodSignature)pjp.getSignature();
		return signature.getMethod();
	}
}
