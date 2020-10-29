package com.cn.weixuan.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        MyUsernamePasswordToken noPasswordToken = (MyUsernamePasswordToken) token;
        //如果是免密，就不需要核对密码了
        if (noPasswordToken.getLoginType().equals(LoginType.SMS)) {
            return true;
        }
        return super.doCredentialsMatch(token, info);

    }
}


