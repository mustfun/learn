package com.dzy.validation.handle;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.dzy.model.ReturnBase;
import com.dzy.validation.annotation.Validation;
import com.dzy.validation.enumeration.ValidationEnum;
import com.dzy.validation.validator.Validator;

/**
 * @FileName: ValidationHandler.java
 * @Function:TODO
 * @date: 2015年12月21日 下午4:00:00
 * @author: gaohuiyu
 * @since JDK 1.8
 */
public class ValidationHandler {

	private static final String RETURN_FAIL = "-1";
	private static final String RETURN_SUC = "1";
	
	public ReturnBase handler(String request, Method method) {
		ReturnBase returnBase = new ReturnBase();
		try {
			// 验证请求参数字符串是否符合json格式
			JSON.parseObject(request, Map.class);
			// 加载校验器配置信息
			Map<Class<? extends Annotation>, Class<? extends Validator>> enumMap = loadValidationConfig();
			// 获取请求方法上声明的注解
			Annotation[] annotations = method.getDeclaredAnnotations();
			if (annotations != null && annotations.length > 0) {
				for (Annotation annotaion : method.getDeclaredAnnotations()) {
					Class<?> annotationClass = annotaion.getClass();
					if (enumMap.containsKey(annotationClass)) {
						// 根据注解类型匹配对应的校验器
						Constructor<? extends Validator> constructor = enumMap.get(annotationClass).getConstructor(Annotation.class);
						Validator validator = constructor.newInstance(annotaion);
						returnBase = validator.valid(request);
						if (!RETURN_SUC.equals(returnBase.getFlag())) {
							return returnBase;
						}
					}
				}
				
				// 处理自定义验证器，必须实现Validator接口
				Validation customValidation = method.getDeclaredAnnotation(Validation.class);
				if (customValidation != null) {
					Validator validator = customValidation.value().newInstance();
					return validator.valid(request);
				}
			}
		} catch (JSONException e) {
			returnBase.setFlag(RETURN_FAIL);
			returnBase.setMsg("json parse fail");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnBase;
	}
	
	private Map<Class<? extends Annotation>, Class<? extends Validator>> loadValidationConfig() {
		//先实例化一个hashmap
		Map<Class<? extends Annotation>, Class<? extends Validator>> enumMap = new HashMap<>();
		//从枚举类中取出值，然后丢在枚举数组
		ValidationEnum[] enums = ValidationEnum.values();
		for (ValidationEnum enumItem : enums) {
			// 读取校验器枚举配置
			enumMap.put(enumItem.getAnnotation(), enumItem.getValidator());
		}
		//map里面是  annotation类==》validator类这样的形式,且根据枚举定义，只有一个
		return enumMap;
	}
	
	private ValidationHandler() {
		
	}
	
	private static ValidationHandler instance;
	public static ValidationHandler getInstance() {
		if (instance == null) {
			return new ValidationHandler();
		}
		return instance;
	}
}
