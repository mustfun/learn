package com.dzy.validation.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import com.dzy.validation.validator.Validator;


/**
 * @FileName: Validation.java
 * @Function:TODO
 * @date: 2015年12月21日 下午3:44:54
 * @author: gaohuiyu
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
public @interface Validation {

	/**
	 * only primitive type,
	 *  String, Class, annotation,
	 *   enumeration are permitted 
	 * @return
	 */
	Class<? extends Validator> value();
}
