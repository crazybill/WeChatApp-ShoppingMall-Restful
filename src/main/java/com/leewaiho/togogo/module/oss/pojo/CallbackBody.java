package com.leewaiho.togogo.module.oss.pojo;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/10/4
 * Project togogo-shixun
 */
public class CallbackBody {
    
    private String key;
    private String hash;
    private String bucket;
    private String fsize;
    
    public CallbackBody() {
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getHash() {
        return hash;
    }
    
    public void setHash(String hash) {
        this.hash = hash;
    }
    
    public String getBucket() {
        return bucket;
    }
    
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
    
    public String getFsize() {
        return fsize;
    }
    
    public void setFsize(String fsize) {
        this.fsize = fsize;
    }
    
    @Override
    public String toString() {
        return "CallbackBody{" +
                       "key='" + key + '\'' +
                       ", hash='" + hash + '\'' +
                       ", bucket='" + bucket + '\'' +
                       ", fsize='" + fsize + '\'' +
                       '}';
    }
}
