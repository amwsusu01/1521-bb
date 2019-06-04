package com.hanergy.reportForms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:entrust-config.properties")
public class JDBCInfoConfig {

	//1521报表系统
	@Value("${entrust.config.jdbc.url}")
	private String url;
	@Value("${entrust.config.jdbc.username}")
	private String username;
	@Value("${entrust.config.jdbc.password}")
	private String password;

	//
	@Value("${psm.config.jdbc.url}")
	private String psmUrl;
	@Value("${psm.config.jdbc.username}")
	private String psmUserName;
	@Value("${psm.config.jdbc.password}")
	private String psmPassword;
	
	public String getPsmUrl() {
		return psmUrl;
	}

	public void setPsmUrl(String psmUrl) {
		this.psmUrl = psmUrl;
	}

	public String getPsmUserName() {
		return psmUserName;
	}

	public void setPsmUserName(String psmUserName) {
		this.psmUserName = psmUserName;
	}

	public String getPsmPassword() {
		return psmPassword;
	}

	public void setPsmPassword(String psmPassword) {
		this.psmPassword = psmPassword;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
