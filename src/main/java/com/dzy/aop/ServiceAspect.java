package com.dzy.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.logging.Log;

@Aspect
@Component
public class ServiceAspect {
	/**
	 * @author dengzhiyuan
	 * @version ServiceAspect.java
	 * @since 2.0
	 */
	private static final Logger LOG = Logger.getLogger(ServiceAspect.class);

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	// 第一个代表返回任意类型，第二个代表controller下面所有，第三个表示所有方法
	// @Pointcut("execution(* com.dzy.service.impl..*.*(..))")

	// 上面一种方式是可行的，下面用注解来弄试试
	//@Pointcut(value = "@annotation(com.dzy.validation.annotation.NotEmpty)")
	
	@Pointcut("execution(* com.dzy.service.impl..*.*(..))")
	public void aspect() {
	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint) {
		if (LOG.isInfoEnabled()) {
			LOG.info("before " + joinPoint);
		}
		System.out.println("切入时候前置通知");
	}

	// 配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint) {
		if (LOG.isInfoEnabled()) {
			LOG.info("after " + joinPoint);
		}
	}

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public Object around(JoinPoint joinPoint) {
		long start = System.currentTimeMillis();
		Object returnValues=null;
		try {
			returnValues=((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			if (LOG.isInfoEnabled()) {
				LOG.info("around " + joinPoint + "\tUse time : "
						+ (end - start) + " ms!");
			}
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			if (LOG.isInfoEnabled()) {
				LOG.info("around " + joinPoint + "\tUse time : "
						+ (end - start) + " ms with exception : "
						+ e.getMessage());
			}
		}
		//这个value就是我们方法执行之后的返回值
		LOG.info("------------------------"+returnValues);
		return returnValues;
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint) {
		if (LOG.isInfoEnabled()) {
			LOG.info("afterReturn " + joinPoint);
		}
	}

	// 配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
		
		if (LOG.isInfoEnabled()) {
			LOG.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
		}
	}

}
