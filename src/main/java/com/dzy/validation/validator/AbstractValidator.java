package com.dzy.validation.validator;

import java.lang.annotation.Annotation;


/**
 * @FileName: AbstractValidator.java
 * @Function:
 * @date: 2015年12月23日 上午11:05:20
 * @author: gaohuiyu
 * @since JDK 1.8
 * @include 一个抽象类包含一个annotation,继承validator接口
 */
public abstract class AbstractValidator implements Validator {

	protected Annotation annotation;

	public AbstractValidator(Annotation annotation) {
		this.annotation = annotation;
	}
	
	
	
}
