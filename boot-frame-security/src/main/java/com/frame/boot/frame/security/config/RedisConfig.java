package com.frame.boot.frame.security.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frame.boot.frame.security.properties.RedisExpirProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.format.support.DefaultFormattingConversionService;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	@Autowired
	private RedisExpirProperties redisExpirProperties;

	@Bean
    @Primary
    public CacheManager cacheManager() {
        RedisSerializationContext.SerializationPair<String> keySerializer = RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer());
        RedisSerializationContext.SerializationPair<Object> valueSerializer = RedisSerializationContext.SerializationPair.fromSerializer(createJackson2JsonRedisSerializer());

        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(keySerializer)
                .serializeValuesWith(valueSerializer);
        if (redisExpirProperties.getDefaultConfig() != null) {
            defaultCacheConfig.entryTtl(Duration.ofSeconds(redisExpirProperties.getDefaultConfig()));
        }

		Map<String, RedisCacheConfiguration> cacheConfigMap = null;
		if (redisExpirProperties.getConfig() != null && !redisExpirProperties.getConfig().isEmpty()) {
			cacheConfigMap = new HashMap<>();
			for (Map.Entry<String, Long> config : redisExpirProperties.getConfig().entrySet()) {
				RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
						.prefixKeysWith(config.getKey())
						.entryTtl(Duration.ofSeconds(config.getValue()))
						.serializeKeysWith(keySerializer)
						.serializeValuesWith(valueSerializer);
				cacheConfigMap.put(config.getKey(), cacheConfig);
			}
		}
		if (cacheConfigMap != null && !cacheConfigMap.isEmpty()) {
			return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory), RedisCacheConfiguration.defaultCacheConfig(), cacheConfigMap);
		} else {
			return new RedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory), RedisCacheConfiguration.defaultCacheConfig());
		}
	}

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        template.setValueSerializer(createJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    private Jackson2JsonRedisSerializer createJackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(createObjectMapper());
        return jackson2JsonRedisSerializer;
    }

    private ObjectMapper createObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return om;
    }
}
