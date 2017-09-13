package com.leewaiho.togogo.common.exception;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
public class CheckException extends RuntimeException {
    public CheckException() {
        super();
    }
    
    public CheckException(String message) {
        super(message);
    }
    
    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CheckException(Throwable cause) {
        super(cause);
    }
    
    protected CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
