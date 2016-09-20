package com.dzy.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
// 这个注解表示notEmpty应该放在方法上面
public @interface NotEmpty {

	// 这个注解，参数是string数组
	String[] value();
}
