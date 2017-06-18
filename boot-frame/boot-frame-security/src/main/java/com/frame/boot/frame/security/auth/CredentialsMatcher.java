package com.frame.boot.frame.security.auth;

import com.frame.boot.frame.security.exception.SecurityException;
import com.frame.common.frame.utils.EmptyUtil;
import com.frame.common.frame.utils.EncodeAndDecodeUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Component;

@Component
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        // 获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        String inPassword = null;
        try {
            inPassword = EncodeAndDecodeUtil.md5(new String(utoken.getPassword()));
        } catch (Exception e) {
            throw new SecurityException(SecurityException.USERNAME_PWD_NOT_SUCC_CODE, "password md5 encode error.", SecurityException.USERNAME_PWD_NOT_SUCC_MSG);
        }
        // 获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        // 进行密码的比对
        if (EmptyUtil.isEmpty(dbPassword) || EmptyUtil.isEmpty(inPassword)) {
            return false;
        } else {
            return this.equals(inPassword, dbPassword);
        }
    }
}
