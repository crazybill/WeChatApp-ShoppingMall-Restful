package com.leewaiho.togogo.common.exception;

import com.leewaiho.togogo.common.Const.ServiceCode;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public class ServiceException extends CheckException {
    
    private ServiceCode code;
    
    public ServiceException(ServiceCode code) {
        super(code.getMessage());
        this.code = code;
    }
    
    public ServiceException(String message) {
        super(message);
        this.code = ServiceCode.FAILED;
    }
    
    public ServiceException(ServiceCode code, String message) {
        super(message);
        this.code = code;
    }
    
    public ServiceException() {
        super();
    }
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ServiceException(Throwable cause) {
        super(cause);
    }
    
    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    public ServiceCode getCode() {
        return code;
    }
    
    public void setCode(ServiceCode code) {
        this.code = code;
    }
}
