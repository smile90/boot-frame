package com.frame.boot.frame.security.filter;


import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.security.entity.SysUser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class RequestSecurityFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            JSONObject bossAuth = new JSONObject();
            if (auth != null) {
//                if (auth.getPrincipal() instanceof  SysUser) {
//                    SysUser user = (SysUser) auth.getPrincipal();
//                    bossAuth.put("username", user.getUsername());
//                }
                bossAuth.put("username", auth.getName());
                context.addZuulRequestHeader("bossAuth", bossAuth.toJSONString());
            }
        } catch (Exception e) {
            logger.error("set bossAuth is error.", e);
        }
        return null;
    }
}
