package com.dzy.hystrixSupport.context.cglib;

import java.lang.reflect.Method;

public class Entry {
	
	private String config;
	private Method method;
	private Method failMethod;
	
	public String getConfig() {
		return config;
	}
	public void setConfig(String config) {
		this.config = config;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	public Method getFailMethod() {
		return failMethod;
	}
	public void setFailMethod(Method failMethod) {
		this.failMethod = failMethod;
	}
	
}
