
package com.dzy.util.crypto;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @FileName: MD5Util.java
 * @date: 2015年2月9日 下午5:24:07
 * @author: zhangchi
 */
public class MD5Util {
	private static final Logger logger = Logger.getLogger(MD5Util.class);
	/**
	 * 
	 * @Title: toMD5
	 * @Description: TODO
	 * @param str
	 * @return md5Str
	 */
	public static String toMD5(String str) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			return null;
		}
		md5.update(str.getBytes());
		byte[] md5Bytes = md5.digest();
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 
	 * @Title: equalsMD5
	 * @Description: TODO
	 * @param str
	 * @param md5
	 * @return result
	 */
	public static boolean equalsMD5(String str, String md5) {
		if (str.isEmpty() || md5.isEmpty()) {
			return false;
		}
		return toMD5(str).toUpperCase().equals(md5.toUpperCase());
	}
	
	/** @param source 需要加密的字符串
	   * @param hashType  加密类型 （MD5 和 SHA）
	   * @return
	   */
	  public static String getHash2(String source, String hashType) {
	    StringBuilder sb = new StringBuilder();
	    MessageDigest md5;
	    try {
	      md5 = MessageDigest.getInstance(hashType);
	      md5.update(source.getBytes());
	      for (byte b : md5.digest()) {
	        sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
	      }
	      return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	  
	  
	  /** @param source 需要加密的字符串
	   * @param hashType  加密类型 （MD5 和 SHA）
	   * @return
	   */
	  public static String getHash2(String source) {
	    StringBuilder sb = new StringBuilder();
	    MessageDigest md5;
	    try {
	      md5 = MessageDigest.getInstance("MD5");
	      md5.update(source.getBytes());
	      for (byte b : md5.digest()) {
	        sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
	      }
	      return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	

}
