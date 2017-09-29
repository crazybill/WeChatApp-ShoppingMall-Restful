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
                       ", data=" + data +
                       '}';
    }
    
}
