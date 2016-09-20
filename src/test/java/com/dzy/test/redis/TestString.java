/*
 * Copyright (c) 2009-2016. 上海诺诺镑客 All rights reserved.
 * @(#) TestString.java 下午4:54:42
 */
package com.dzy.test.redis;
/**
 * @author dengzhiyuan
 * @version TestString.java
 * @since 2.0
 */

public class TestString {

	
	public static void main(String[] args) {
		String timeString="2016-03-28 18:07:53";
		String[] split = timeString.split(" ")[0].split("-");
		for (String string : split) {
			
			System.out.println(string);
		}
	}
	
}
