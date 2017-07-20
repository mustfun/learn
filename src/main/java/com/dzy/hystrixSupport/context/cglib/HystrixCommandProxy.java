package com.dzy.hystrixSupport.context.cglib;

import com.dzy.hystrixSupport.context.ApplicationContextHolder;
import com.dzy.hystrixSupport.hystrix.HystrixConfig;
import com.dzy.hystrixSupport.hystrix.HystrixFatory;
import com.netflix.hystrix.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class HystrixCommandProxy<T> implements MethodInterceptor {

	private final static Logger LOGGER = Logger.getLogger(HystrixCommandProxy.class);
	
	private Enhancer enhancer = new Enhancer();
	
	protected Set<Entry> set = new HashSet<Entry>();
	
	private T target;
	
	@SuppressWarnings("unchecked")
	public T getProxy(T target, Set<Entry> set) {
		this.set = set;
		this.target = target;
		
		enhancer.setSuperclass(target.getClass());
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		for (Entry entry : set) {
			// 执行方法
			Method method = entry.getMethod();
			// 降级方法
			Method failMethod = entry.getFailMethod();
			String config = entry.getConfig();
			
			if (arg1.getName().equals(method.getName()) && arg1.getParameterCount() == method.getParameterCount()) {
				try {
					HystrixConfig hystrixConfig = (HystrixConfig) ApplicationContextHolder.getApplicationContext().getBean(config);
					if (hystrixConfig != null) {
						HystrixFatory hystrixFatory = new HystrixFatory(hystrixConfig);
						HystrixCommand<Object> command = hystrixFatory.create(arg3, arg0, arg2, failMethod, target);
						Object result = command.execute();
						return result;
					} else {
						LOGGER.error("断路器配置：" + config + "没有被找到");
					}
					
					break;
				} catch (Exception e) {
					LOGGER.error("断路器：" + config + "在执行方法：" + method.getName() + "时发生异常");
					e.printStackTrace();
				}
			}
		}
		
		return arg3.invokeSuper(arg0, arg2);
	}

}
