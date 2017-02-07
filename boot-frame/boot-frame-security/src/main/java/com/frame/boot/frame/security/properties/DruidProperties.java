package com.frame.boot.frame.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

@Configuration
@PropertySource(value = {"classpath:/config/dataSource.properties"}, ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidProperties {

    private boolean loginEnable = false;
    private String loginUsername = "admin";
	private String loginPassword = "admin";
	private String exclusions;

	public boolean isLoginEnable() {
		return loginEnable;
	}

	public void setLoginEnable(boolean loginEnable) {
		this.loginEnable = loginEnable;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getExclusions() {
		return exclusions;
	}

	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("DruidProperties{");
		sb.append("loginEnable=").append(loginEnable);
		sb.append(", loginUsername='").append(loginUsername).append('\'');
		sb.append(", loginPassword='").append(loginPassword).append('\'');
		sb.append(", exclusions='").append(exclusions).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
