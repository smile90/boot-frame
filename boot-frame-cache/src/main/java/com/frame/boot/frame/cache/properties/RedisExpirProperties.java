package com.frame.boot.frame.cache.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "redis.expir", ignoreInvalidFields = true)
public class RedisExpirProperties {
	
	private Long defaultConfig;
	private Map<String, Long> config = new HashMap<>();
	
	public Long getDefaultConfig() {
		return defaultConfig;
	}
	
	public void setDefaultConfig(Long defaultConfig) {
		this.defaultConfig = defaultConfig;
	}
	
	public Map<String, Long> getConfig() {
		return config;
	}
	
	public void setConfig(Map<String, Long> config) {
		this.config = config;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("RedisProperties{");
		sb.append("defaultConfig=").append(defaultConfig);
		sb.append(", config=").append(config);
		sb.append('}');
		return sb.toString();
	}
}
