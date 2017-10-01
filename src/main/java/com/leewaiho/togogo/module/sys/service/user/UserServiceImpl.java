package com.leewaiho.togogo.module.sys.service.user;

import com.leewaiho.togogo.common.Const;
import com.leewaiho.togogo.common.base.service.BaseServiceImpl;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.util.CheckUtils;
import com.leewaiho.togogo.common.util.StringUtils;
import com.leewaiho.togogo.module.sys.model.user.TSRole;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.sys.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/15
 * Project togogo-shixun
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<TSUser> implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    
    @Override
    public TSUser findByUsername(String username) {
        TSUser byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            throw new ServiceException(Const.ServiceCode.NOTFOUND, String.format("不存在用户名为 %s 的用户", username));
        }
        return byUsername;
    }
    
    @Override
    public TSUser findByOpenId(String openId) {
        TSUser byOpenId = userRepository.findByOpenId(openId);
        if (byOpenId == null) {
            log.error("微信用户未注册 OpenId : {}", openId);
            throw new ServiceException(Const.ServiceCode.NOTFOUND, "微信用户未注册");
        }
        return byOpenId;
    }
    
    @Override
    public TSUser findByMobilePhone(String mobilePhone) {
        TSUser byMobilePhone = userRepository.findByMobilePhone(mobilePhone);
        if (byMobilePhone == null) {
            String message = String.format("%s 手机用户不存在", mobilePhone);
            log.error(message);
            throw new ServiceException(Const.ServiceCode.NOTFOUND, message);
        }
        return byMobilePhone;
    }
    
    @Override
    public boolean mobilePhoneCanUsed(String mobilePhone) {
        CheckUtils.check(!StringUtils.isEmpty(mobilePhone), "手机号码不能为空");
        CheckUtils.check(StringUtils.isPhoneLegal(mobilePhone), "手机号码格式不合法");
        return true;
    }
    
    @Override
    protected void beforeSave(TSUser tsUser) {
        CheckUtils.check(mobilePhoneCanUsed(tsUser.getMobilePhone()) &&
                                 userRepository.findByMobilePhone(tsUser.getMobilePhone()) == null, "该手机号码已经被占用");
        CheckUtils.check(!StringUtils.isEmpty(tsUser.getOpenId()) &&
                                 userRepository.findByOpenId(tsUser.getOpenId()) == null, "该微信用户已经注册");
    
        TSRole roleUser = roleService.findByRoleKey("ROLE_USER");
        Set<TSRole> userRoles = tsUser.getRoles();
        if (userRoles == null || userRoles.size() == 0) {
            userRoles = new HashSet<>();
            userRoles.add(roleUser);
        } else {
            if (!userRoles.contains(roleUser)) {
                userRoles.add(roleUser);
            }
        }
        tsUser.setRoles(userRoles);
        super.beforeSave(tsUser);
    }
}
