package com.dzy.validation.validator;

import com.dzy.model.ReturnBase;

/**
 * @FileName: Validator.java
 * @Function:TODO
 * @date: 2015年12月21日 下午3:50:14
 * @author: gaohuiyu
 * @since JDK 1.8
 */
public interface Validator {

	public ReturnBase valid(String request);
}
