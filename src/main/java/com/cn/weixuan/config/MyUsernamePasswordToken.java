package com.cn.weixuan.config;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author YHY
 */
public class MyUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = 1L;

    private LoginType loginType;

    public MyUsernamePasswordToken() {
        super();
    }

    public MyUsernamePasswordToken(String username, LoginType loginType) {
        super.setUsername(username);
        this.loginType = loginType;
    }

    public MyUsernamePasswordToken(String username, String password, LoginType loginType) {
        super(username, (char[]) (password != null ? password.toCharArray() : null), false, (String) null);
        this.loginType = loginType;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
