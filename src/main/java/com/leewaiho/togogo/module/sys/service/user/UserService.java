package com.leewaiho.togogo.module.sys.service.user;


import com.leewaiho.togogo.common.base.service.BaseService;
import com.leewaiho.togogo.module.sys.model.user.TSUser;

/**
 * @Author leewaiho
 * @Email 791783391@qq.com
 * @Date 2017/9/15
 */
public interface UserService extends BaseService<TSUser> {
    
    TSUser findByUsername(String username);
    
    TSUser findByOpenId(String openId);
    
    TSUser findByMobilePhone(String mobilePhone);
    
    /**
     * 检查手机号码是否已经被占用, 未被占用返回true
     * @param mobilePhone 手机号码
     * @return 可用true 否则false
     */
    boolean mobilePhoneCanUsed(String mobilePhone);
}
