package com.cn.weixuan.web.exception;

/**
 * @author ：YHY
 * @date ：2020/10/12 10:23
 * @description：登录异常
 */
public class LoginException extends RuntimeException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
