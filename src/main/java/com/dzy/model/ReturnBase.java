package com.dzy.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

//返回给接口的
public class ReturnBase  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7503364488322218536L;
	//返回的标志位 1表示正常返回  -1表示非正常返回  如有特殊的情况，请私下约定
	private String flag;
	//返回给前台的提示的信息
	private String msg;
	//返回给前台的数据信息，默认为空
	private Map<String, Object> data = new HashMap<String, Object>();
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
    @Override
    public String toString() {
    	
    	StringBuilder sb = new StringBuilder("");
    	sb.append("flag:" + this.getFlag() +"\n");
    	sb.append("msg:" + this.getMsg() +"\n");
    	sb.append("data:" + JSON.toJSONString(this.getData()) + "\n");
    	return  sb.toString();
    }
    
    //构造函数
    public ReturnBase(String flag){
    	
    	this.setFlag(flag);
    		
    }
    //构造函数
    public ReturnBase(String flag,String msg){
    	
    	this.setFlag(flag);
    	this.setMsg(msg);
    		
    }
    //默认构造函数
    public ReturnBase(){
    	
    	this.setFlag("1");
    		
    }
	
	

}
