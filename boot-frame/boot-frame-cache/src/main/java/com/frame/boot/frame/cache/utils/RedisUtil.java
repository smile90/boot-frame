package com.frame..boot.frame.cache.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
	
	private static RedisUtil redis;
	
	@Autowired
	private StringRedisTemplate template;
	
	@PostConstruct
	public void init(){
		redis = this;
		redis.template = this.template;
	}
	
	private static final int SEQUENCE_INCREMENT_STEP = 1;
	
	public static String get(String key) {
		return redis.template.boundValueOps(key).get();
	}
	
	public static void set(String key, String value) {
		redis.template.boundValueOps(key).set(value);
	}
	
	public static void set(String key, String value, long timeout, TimeUnit unit) {
		redis.template.boundValueOps(key).set(value, timeout, unit);
	}
	
	public static Long increment(String key) {
		return redis.template.boundValueOps(key).increment(SEQUENCE_INCREMENT_STEP);
	}
}
