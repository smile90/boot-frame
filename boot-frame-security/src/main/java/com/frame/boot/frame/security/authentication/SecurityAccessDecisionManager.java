package com.frame.boot.frame.security.authentication;

import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("securityAccessDecisionManager")
public class SecurityAccessDecisionManager implements AccessDecisionManager {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.info("{},{}", authentication, authentication.getClass());
        logger.info("{},{}", object, object.getClass());
        logger.info("{}", configAttributes);
        if (EmptyUtil.notEmpty(configAttributes)) {
            for (ConfigAttribute configAttribute : configAttributes) {
                logger.info("{},{}", configAttribute, configAttribute.getClass());
            }
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
