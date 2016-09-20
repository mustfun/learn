package com.dzy.test.ordiry;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author dengzhiyuan
 * @version TaskTest.java
 * @since 2.0
 */

public class TaskTest {

	public static void main(String[] args) {
		/*for (int i = 0; i < 5; i++) {
			System.out.println("nihao");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		ti=new Timer();
		ti.schedule(timerTask,0,5000);
		System.out.println("你什么时候打印");
	
		
	}
	private static  Timer ti;
	private static int  a=1;
	
	private static TimerTask timerTask=new TimerTask() {
		
		@Override
		public void run() {
			a++;
			if (a<=7) {
				System.out.println("我好");
			}else{
				System.out.println("byebye");
				ti.cancel();
			}
		}
	};
	
	
	private static int getAgeByIdcard(String idcard){
		//获取年龄
		Calendar cal=Calendar.getInstance();
		   // 当前年  
	    int year = cal.get(Calendar.YEAR);  
	    // 当前月  
	    int month = (cal.get(Calendar.MONTH)) + 1;  
	    // 当前月的第几天：即当前日  
	    int day_of_month = cal.get(Calendar.DAY_OF_MONTH); 

		int age = year -Integer.parseInt(idcard.substring(6, 10)) - 1;
		if (Integer.parseInt(idcard.substring(10, 12)) < month ||Integer.parseInt(idcard.substring(10, 12))  == month && Integer.parseInt(idcard.substring(12, 14))  <= day_of_month) {
		   age++;
		}
       return age;
	}

	
}
