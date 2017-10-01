package com.leewaiho.togogo.common.pojo;

import com.alibaba.druid.util.StringUtils;
import com.leewaiho.togogo.common.Const.ServiceCode;
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
    
    private String message = ServiceCode.SUCCESS.getMessage();
    private boolean success = ServiceCode.SUCCESS.isSuccess();
    private int code = ServiceCode.SUCCESS.getCode();
    private T data;
    
    public Result() {
    }
    
    public Result(T data, ServiceCode code) {
        this.success = code.isSuccess();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
    }
    
    public Result(T data, ServiceCode code, String message) {
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
    
    public static <T> Result<T> success(T data) {
        return success(data, null);
    }
    
    public static <T> Result<T> success(T data, String message) {
        return success(ServiceCode.SUCCESS, data, message);
    }
    
    public static <T> Result<T> success(ServiceCode code, T data) {
        return success(code, data, null);
    }
    
    public static <T> Result<T> success(ServiceCode code, T data, String message) {
        Result result = new Result<>();
        result.setCode(code.getCode());
        result.setSuccess(code.isSuccess());
        result.setMessage(code.getMessage());
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
        } else {
            result.setData(data);
        }
        return result;
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
    
}
