package com.dzy.validation.enumeration;
import java.lang.annotation.Annotation;

import com.dzy.validation.annotation.NotEmpty;
import com.dzy.validation.validator.NotEmptyValidator;
import com.dzy.validation.validator.Validator;


/**
 * @FileName: ValidationEnum.java
 * @Function:TODO
 * @date: 2015年12月21日 下午4:01:26
 * @author: gaohuiyu
 * @since JDK 1.8
 */
public enum ValidationEnum {

	NOT_EMPTY(NotEmpty.class, NotEmptyValidator.class);  //no_empty属性，包含2个参数
	
	private Class<? extends Annotation> annotation;
	private Class<? extends Validator> validator;
	
	private ValidationEnum(Class<? extends Annotation> annotation, Class<? extends Validator> validator) {
		this.annotation = annotation;
		this.validator = validator;
	}

	public Class<? extends Annotation> getAnnotation() {
		return annotation;
	}

	public Class<? extends Validator> getValidator() {
		return validator;
	}
	
}
