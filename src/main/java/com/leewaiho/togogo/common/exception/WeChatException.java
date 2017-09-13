package com.leewaiho.togogo.common.exception;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
public class WeChatException extends CheckException {
    public WeChatException() {
        super();
    }
    
    public WeChatException(String message) {
        super(message);
    }
    
    public WeChatException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public WeChatException(Throwable cause) {
        super(cause);
    }
    
    protected WeChatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
