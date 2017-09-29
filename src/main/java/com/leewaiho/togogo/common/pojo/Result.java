package com.leewaiho.togogo.common.pojo;

import com.alibaba.druid.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/13
 */
public class Result<T> implements Serializable {
    
    private String message = Code.SUCCESS.getMessage();
    private boolean success = Code.SUCCESS.isSuccess();
    private int code = Code.SUCCESS.getCode();
    private T data;
    
    public Result() {
    }
    
    public Result(T data, Code code) {
        this.success = code.isSuccess();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
    }
    
    public Result(T data, Code code, String message) {
        this(data, code);
        this.message = message;
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
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public static <T> Result<T> success(T data, String message) {
        Result result = new Result<>();
        if (!StringUtils.isEmpty(message))
            result.setMessage(message);
        if (data instanceof PageImpl) {
            Page<T> page = (PageImpl<T>) data;
            Map<String, Object> dataDetail = new LinkedHashMap<>();
            dataDetail.put("content", page.getContent());
            Map<String, Object> pageInfo = new LinkedHashMap<>();
            pageInfo.put("page", page.getNumber());
            pageInfo.put("size", page.getSize());
            pageInfo.put("totalPages", page.getTotalPages());
            pageInfo.put("totalElements", page.getTotalElements());
            pageInfo.put("first", page.isFirst());
            pageInfo.put("last", page.isLast());
            dataDetail.put("pageInfo", pageInfo);
            result.setData(dataDetail);
            return result;
        }
        result.setData(data);
        return result;
    }
    
    public static <T> Result<T> success(T data) {
        return success(data, null);
    }
    
    @Override
    public String toString() {
        return "Result{" +
                       "message='" + message + '\'' +
                       ", success=" + success +
                       ", code=" + code +
                       ", data=" + data +
                       '}';
    }
    
    public enum Code {
        FAILED(10000, "操作失败", false), UNLOGIN(11000, "用户未登录", false),
        UNREGISTER(11010, "用户未注册", false), SUCCESS(20000, "操作成功"),
        LOGIN(21000, "登录成功"), REGISTERD(21010, "注册成功");
        
        private int code;
        private String message;
        private boolean success;
        
        Code(int code, String message) {
            this.code = code;
            this.message = message;
            this.success = true;
        }
        
        Code(int code, String message, boolean success) {
            this.code = code;
            this.message = message;
            this.success = success;
        }
        
        public int getCode() {
            return code;
        }
        
        public String getMessage() {
            return message;
        }
        
        public boolean isSuccess() {
            return success;
        }
        
        @Override
        public String toString() {
            return "Code{" +
                           "code=" + code +
                           ", message='" + message + '\'' +
                           ", success=" + success +
                           "} " + super.toString();
        }
    }
    
}
