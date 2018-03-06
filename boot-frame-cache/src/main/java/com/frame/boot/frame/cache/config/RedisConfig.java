package com.frame.boot.frame.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frame.boot.frame.cache.properties.RedisExpirProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	private RedisProperties redisProperties;
	
	@Autowired
	private RedisExpirProperties redisExpirProperties;
		
	@Bean
	public RedissonClient getRedissonClient() {
		Config config = new Config();
		// 哨兵模式
		RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
		if (sentinel != null) {
			SentinelServersConfig sentinelConfig = config.useSentinelServers();
			sentinelConfig.setMasterName(sentinel.getMaster())
					.setDatabase(redisProperties.getDatabase())
					.setConnectTimeout(redisProperties.getTimeout());
			if (redisProperties.getPassword() != null && !redisProperties.getPassword().isEmpty()) {
				sentinelConfig.setPassword(redisProperties.getPassword());
			}
			for (String node : sentinel.getNodes().split(",")) {
				sentinelConfig.addSentinelAddress(node);
			}
		// 单一模式
		} else {
			SingleServerConfig singleServerConfig = config.useSingleServer();
			singleServerConfig.setAddress(redisProperties.getHost() + ":" + redisProperties.getPort())
					.setDatabase(redisProperties.getDatabase())
					.setConnectTimeout(redisProperties.getTimeout());
			if (redisProperties.getPassword() != null && !redisProperties.getPassword().isEmpty()) {
				singleServerConfig.setPassword(redisProperties.getPassword());
			}
		}

		return Redisson.create(config);
	}
	
	@Bean
	public CacheManager cacheManager() {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
		if (redisExpirProperties.getDefaultConfig() != null) {
			cacheManager.setDefaultExpiration(redisExpirProperties.getDefaultConfig());
		}
		if (redisExpirProperties.getConfig() != null) {
			cacheManager.setExpires(redisExpirProperties.getConfig());
		}
		return cacheManager;
	}
	
	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
}
