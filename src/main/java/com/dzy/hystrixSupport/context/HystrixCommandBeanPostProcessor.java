package com.dzy.hystrixSupport.context;

import com.dzy.hystrixSupport.annotation.HystrixCommand;
import com.dzy.hystrixSupport.constant.enums.HystrixType;
import com.dzy.hystrixSupport.context.cglib.Entry;
import com.dzy.hystrixSupport.context.cglib.HystrixCommandProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@Component
public class HystrixCommandBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Class<?> clazz = bean.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		if (methods != null && methods.length > 0) {
			
			Set<Entry> set = new HashSet<Entry>();
			boolean isHystrix = false;
			
			for (Method method : methods) {
				HystrixCommand hystrixCommand = method.getAnnotation(HystrixCommand.class);
				if (hystrixCommand != null) {
					
					HystrixType type = hystrixCommand.type();
					Entry entry = null;
					for (Entry item : set) {
						if (hystrixCommand.config().equals(item.getConfig())) {
							entry = item;
							break;
						}
					}
					if (entry == null) {
						entry = new Entry();
						entry.setConfig(hystrixCommand.config());
						set.add(entry);
					}
					
					if (type == HystrixType.SUCCESS) {
						entry.setMethod(method);
					} else {
						entry.setFailMethod(method);
					}
					
					isHystrix = true;
				}
			}
			if (isHystrix) {
				bean = new HystrixCommandProxy<Object>().getProxy(bean, set);
			}
			
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		return bean;
	}
	
}
