package com.dzy.validation.validator;
import java.lang.annotation.Annotation;

import com.alibaba.fastjson.JSON;
import com.dzy.model.ReturnBase;

/**
 * @FileName: NotEmptyValidator.java
 * @Function:TODO
 * @date: 2015年12月21日 下午4:18:39
 * @author: gaohuiyu
 * @since JDK 1.8
 */
public class NotEmptyValidator extends AbstractValidator {

	//这个构造函数在初始化父类的annotation
	public NotEmptyValidator(Annotation annotation) {
		super(annotation);
	}

	@Override
	public ReturnBase valid(String request) {
		//这里处理验证方法，验证空的验证器
		Object result = JSON.parse(request);
		
		return null;
	}
	
	

}
