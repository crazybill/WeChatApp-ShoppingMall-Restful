package com.leewaiho.togogo.module.wechat.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.leewaiho.togogo.common.Const.*;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.util.OAuth2Util;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.sys.service.user.UserService;
import com.leewaiho.togogo.module.wechat.WeChat;
import com.leewaiho.togogo.module.wechat.dto.RegisterObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import static com.leewaiho.togogo.common.Const.*;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/27
 * Project togogo-shixun
 */
@Service
public class WeChatServiceImpl implements WeChatService {
    
    private static final Logger log = LoggerFactory.getLogger(WeChatService.class);
    
    @Autowired
    private WeChat weChat;
    
    @Autowired
    private UserService userService;
    
    @Override
    public OAuth2AccessToken loginWeChat(String code) {
        try {
            TSUser byOpenId = userService.findByOpenId(getOpenId(code));
            OAuth2AccessToken accessToken = OAuth2Util.getAccessToken(byOpenId.getUsername(), byOpenId.getPassword());
            return accessToken;
        } catch (ServiceException e) {
            if (e.getCode().equals(ServiceCode.NOTFOUND)) {
                throw new ServiceException(ServiceCode.UNREGISTER);
            } else {
                throw new ServiceException(e);
            }
        }
    }
    
    @Override
    public String getOpenId(String code) {
        ResponseEntity<String> responseEntity = weChat.code2Session(code);
        if (responseEntity.getBody() instanceof String) {
            JSONObject response = (JSONObject) JSONObject.parse(responseEntity.getBody());
            if (response.containsKey(WECHAT_OPENID_KEY) && !StringUtils.isEmpty(response.getString(WECHAT_OPENID_KEY))) {
                return response.getString(WECHAT_OPENID_KEY);
            }
            if (response.containsKey(WECHAT_ERRMSG_KEY) && response.containsKey(WECHAT_ERRCODE_KEY)) {
                String errMsg = response.getString(WECHAT_ERRMSG_KEY);
                int errCode = response.getInteger(WECHAT_ERRCODE_KEY);
                log.debug("无法获取OPENID, 错误代码: {}, 错误原因: {}", errCode, errMsg);
                switch (errCode) {
                    case 40029:
                        throw new ServiceException(ServiceCode.WECHAT_INVALID_CODE);
                    case 40163:
                        throw new ServiceException(ServiceCode.WECHAT_CODE_BEEN_USED);
                }
                throw new ServiceException(ServiceCode.FAILED, String.format("登录失败, 原因: %s", errMsg.split(", hints: ")[0]));
            }
        }
        throw new ServiceException(ServiceCode.FAILED, "获取OpenId失败,请检查!");
    }
    
    @Override
    public TSUser registerOnWeChat(RegisterObject object) {
        TSUser user = new TSUser();
        user.setOpenId(getOpenId(object.getWxCode()));
        user.setUsername(object.getUsername());
        user.setPassword(object.getPassword());
        user.setMobilePhone(object.getMobilePhone());
        user.setGender(object.getGender());
        user.setAvatarUrl(object.getAvatarUrl());
        userService.save(user);
        return user;
    }
}
