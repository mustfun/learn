package com.dzy.hystrixSupport.constant.enums;

public enum HystrixType {

	SUCCESS("success"),
	FAIL("fail");
	
	private String name;
	
	private HystrixType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
}
