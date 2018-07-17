package com.frame.boot.frame.security.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.frame.boot.frame.security.auth.CustomJwtAuthenticationToken;
import com.frame.boot.frame.security.constants.RedisKeyConstants;
import com.frame.boot.frame.security.entity.SysUser;
import com.frame.boot.frame.security.properties.SystemSecurityProperties;
import com.frame.boot.frame.security.service.SysUserService;
import com.frame.common.frame.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SystemSecurityProperties systemSecurityProperties;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public Authentication createAuthentication(SysUser sysUser) {
        if (sysUser != null) {
            // 生成JWT Token
            String credentials = createToken(sysUser);
            logger.debug("create token:{}", credentials);
            // Token有效时间
            redisTemplate.boundValueOps(RedisKeyConstants.USERNAME_PRE + sysUser.getUsername()).set(credentials, systemSecurityProperties.getJwt().getShortExp(), TimeUnit.MINUTES);
            // 授权
            return new CustomJwtAuthenticationToken(sysUser, credentials, sysUser.getAuthorities());
        } else {
            return null;
        }
    }

    public String createToken(UserDetails userDetails) {
        if (userDetails != null) {
            SystemSecurityProperties.Jwt jwtCfg = systemSecurityProperties.getJwt();
            //        String authorities = authentication.getAuthorities()
            //                .stream()
            //                .map(authority -> authority.getAuthority())
            //                .collect(Collectors.joining(","));

            Date now = Date.from(Instant.now());
            Date expiration = Date.from(ZonedDateTime.now().plusMinutes(jwtCfg.getExp()).toInstant());

            Algorithm algorithm = Algorithm.HMAC256(systemSecurityProperties.getJwt().getSecret());
            //create jwt
            String jwt = JWT.create()
                    //                .withClaim("authorities", authorities)
                    .withSubject(userDetails.getUsername())
                    .withIssuedAt(now)
                    .withExpiresAt(expiration)
                    .sign(algorithm);
            return jwt;
        } else {
            return null;
        }
    }

    public void refreshTokenExporation(String token) {
        String username = getUsername(token);
        if (EmptyUtil.notEmpty(username)) {
            String redisToken = redisTemplate.boundValueOps(RedisKeyConstants.USERNAME_PRE + username).get();
            if (EmptyUtil.notEmpty(redisToken)) {
                redisTemplate.boundValueOps(RedisKeyConstants.USERNAME_PRE + username).set(token, systemSecurityProperties.getJwt().getShortExp(), TimeUnit.MINUTES);
                logger.info("refresh token:{},redisToken:{}", token, redisToken);
            }
        }
    }

    public String refreshToken(DecodedJWT decodedJWT) {
        SystemSecurityProperties.Jwt jwtCfg = systemSecurityProperties.getJwt();
        Algorithm algorithm = Algorithm.HMAC256(systemSecurityProperties.getJwt().getSecret());

        Date now = Date.from(Instant.now());
        Date expiration = Date.from(ZonedDateTime.now().plusMinutes(jwtCfg.getExp()).toInstant());
        //create jwt
        String jwt = JWT.create()
//                .withClaim("authorities", decodedJWT.getClaim("authorities").asString())
                .withSubject(decodedJWT.getSubject())
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);
        return jwt;
    }

    public boolean validateToken(String token) {
        if (EmptyUtil.isEmpty(token)) {
            return false;
        }
        if (token == null) {
            return false;
        }
        // 校验Token有效期
        String username = getUsername(token);
        if (EmptyUtil.isEmpty(username)) {
            return false;
        }

        try {
            // 校验Token
            Algorithm algorithm = Algorithm.HMAC256(systemSecurityProperties.getJwt().getSecret());
            JWT.require(algorithm).build().verify(token);

            // 校验有效期
            String redisToken = redisTemplate.boundValueOps(RedisKeyConstants.USERNAME_PRE + username).get();
            logger.debug("valid token:{},redisToken:{},boolean:{}", token, redisToken, token.equals(redisToken));
            if (EmptyUtil.notEmpty(redisToken) && redisToken.equals(token)) {
                return true;
            } else {
                return false;
            }
        } catch (TokenExpiredException e) {
            logger.error("token expire. {}", e.getMessage());
            logger.debug("token expire", e);
            // TODO 失效Token处理
            return false;
        } catch (JWTVerificationException e) {
            logger.error("validate token error. {}", e.getMessage());
            logger.debug("validate token error", e);
            return false;
        }
    }

    public void deleteToken(String username) {
        // 设置Token失效
        redisTemplate.delete(username);
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        SysUser sysUser = getUser(request);
        return getAuthentication(sysUser);
    }

    public Authentication getAuthentication(SysUser sysUser) {
        if (sysUser != null) {
            // Token有效时间
            String token = redisTemplate.boundValueOps(RedisKeyConstants.USERNAME_PRE + sysUser.getUsername()).get();
            logger.debug("get token:{}", token);
            if (EmptyUtil.notEmpty(token)) {
                // 授权
                return new CustomJwtAuthenticationToken(sysUser, token, sysUser.getAuthorities());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public SysUser getUser(HttpServletRequest request) {
        String username = getUsername(getToken(request));
        if (EmptyUtil.notEmpty(username)) {
            return sysUserService.findSecurityUserByUsername(username);
        } else {
            return null;
        }
    }

    public String getUsername(HttpServletRequest request) {
        return getUsername(getToken(request));
    }

    public String getToken(HttpServletRequest request) {
        String token = request.getHeader(systemSecurityProperties.getJwt().getRequestKey());
        if (EmptyUtil.notEmpty(token) && !"null".equalsIgnoreCase(token)) {
            return token;
        } else {
            return null;
        }
    }

    public String getUsername(String token) {
        if (EmptyUtil.notEmpty(token)) {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getSubject();
        } else {
            return null;
        }
    }
}