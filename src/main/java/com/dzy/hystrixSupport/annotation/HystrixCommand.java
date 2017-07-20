package com.dzy.hystrixSupport.annotation;


import com.dzy.hystrixSupport.constant.enums.HystrixType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HystrixCommand {

	/**
	 * 断路器配置
	 */
	String config();
	
	HystrixType type() default HystrixType.SUCCESS;
	
}
