package com.dzy.web.config.impl;


import java.io.Serializable;






import org.springframework.ui.context.Theme;

import com.dzy.web.config.WebAppConfigure;

public class DefaultWebAppConfigure implements WebAppConfigure,Serializable {

	
	private static final DefaultWebAppConfigure CONFIG = new DefaultWebAppConfigure();
	
	private String mainDomain;
    private String topPrivateDomain;
    private String cookieDomain;
    private String staticDomain;
    public String getSkinsName() {
		return skinsName;
	}


	public void setSkinsName(String skinsName) {
		this.skinsName = skinsName;
	}


	public String getStylesPath() {
		return stylesPath;
	}


	public void setStylesPath(String stylesPath) {
		this.stylesPath = stylesPath;
	}


	public String getScriptsPath() {
		return scriptsPath;
	}


	public void setScriptsPath(String scriptsPath) {
		this.scriptsPath = scriptsPath;
	}


	public String getImagesPath() {
		return imagesPath;
	}


	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}


	public static DefaultWebAppConfigure getConfig() {
		return CONFIG;
	}


	public void setMainDomain(String mainDomain) {
		this.mainDomain = mainDomain;
	}


	public void setTopPrivateDomain(String topPrivateDomain) {
		this.topPrivateDomain = topPrivateDomain;
	}


	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}


	public void setStaticDomain(String staticDomain) {
		this.staticDomain = staticDomain;
	}


	public void setHttpPort(String httpPort) {
		this.httpPort = httpPort;
	}


	public void setHttpsPort(String httpsPort) {
		this.httpsPort = httpsPort;
	}


	public void setBaseHttpUrl(String baseHttpUrl) {
		this.baseHttpUrl = baseHttpUrl;
	}


	public void setBaseHttpsUrl(String baseHttpsUrl) {
		this.baseHttpsUrl = baseHttpsUrl;
	}


	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}


	public void setStylesBaseUrl(String stylesBaseUrl) {
		this.stylesBaseUrl = stylesBaseUrl;
	}


	public void setScriptsBaseUrl(String scriptsBaseUrl) {
		this.scriptsBaseUrl = scriptsBaseUrl;
	}


	public void setImagesBaseUrl(String imagesBaseUrl) {
		this.imagesBaseUrl = imagesBaseUrl;
	}

	private String httpPort;
    private String httpsPort;
    private String baseHttpUrl;
    private String baseHttpsUrl;
    private String contextPath = "/";

    private String skinsName;
    private String stylesBaseUrl;
    private String scriptsBaseUrl;
    private String imagesBaseUrl;
    private String stylesPath = "/styles";
    private String scriptsPath = "/scripts";
    private String imagesPath = "/images";
    private boolean devMode;
    private String format;
    private String sesameTimeoutSleepTime;
    public void setSesameTimeoutSleepTime(String sesameTimeoutSleepTime) {
		this.sesameTimeoutSleepTime = sesameTimeoutSleepTime;
	}
    public String getSesameTimeoutSleepTime() {
		return sesameTimeoutSleepTime;
	}
			

	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}

	private static final long serialVersionUID = 6242808943394462803L;

    public static DefaultWebAppConfigure getConfigure() {
        return CONFIG;
    }
	
	
	@Override
	public String getMainDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTopPrivateDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCookieDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStaticDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHttpPort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHttpsPort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseHttpUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseHttpsUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getImagesBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStylesBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScriptsBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSkinImagesUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSkinStylesUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isForcedSSLLogin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Theme getTheme() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDevMode() {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * @author dengzhiyuan
	 * @version DefaultWebAppConfigure.java
	 * @since 2.0
	 */
	
}