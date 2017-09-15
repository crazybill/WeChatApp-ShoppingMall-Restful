package com.leewaiho.togogo.common.pojo;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
public class Result<T> {
    private String message = "操作成功";
    private boolean success = true;
    private T data;
    
    public Result() {
    }
    
    public Result(String message, boolean success, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public static Result success(Object data) {
        Result result = new Result<>();
        result.setData(data);
        return result;
    }
    
    @Override
    public String toString() {
        return "Result{" +
                       "message='" + message + '\'' +
                       ", success=" + success +
                       ", data=" + data +
                       '}';
    }
    
}
