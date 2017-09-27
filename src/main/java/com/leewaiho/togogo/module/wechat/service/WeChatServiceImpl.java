package com.leewaiho.togogo.module.wechat.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.leewaiho.togogo.common.Const;
import com.leewaiho.togogo.common.exception.ServiceException;
import com.leewaiho.togogo.common.util.OAuth2Util;
import com.leewaiho.togogo.module.sys.model.user.TSUser;
import com.leewaiho.togogo.module.sys.repository.user.UserRepository;
import com.leewaiho.togogo.module.wechat.WeChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

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
    private UserRepository userRepository;
    
    @Override
    public Object wechatLogin(String code) {
        TSUser byOpenId = userRepository.findByOpenId(getOpenId(code));
        OAuth2AccessToken accessToken = OAuth2Util.getAccessToken(byOpenId.getUsername(), byOpenId.getPassword());
        return accessToken;
    }
    
    @Override
    public String getOpenId(String code) {
        ResponseEntity<String> responseEntity = weChat.code2Session(code);
        if (responseEntity.getBody() instanceof String) {
            JSONObject response = (JSONObject) JSONObject.parse(responseEntity.getBody());
            if (!response.containsKey(Const.WECHAT_OPENID_KEY) || StringUtils.isEmpty(response.getString(Const.WECHAT_OPENID_KEY))) {
                if (response.containsKey(Const.WECHAT_ERRMSG_KEY) && response.containsKey(Const.WECHAT_ERRCODE_KEY)) {
                    String errMsg = response.getString(Const.WECHAT_ERRMSG_KEY);
                    String errCode = response.getString(Const.WECHAT_ERRCODE_KEY);
                    throw new ServiceException(String.format("无法获取OpenId, 错误代码: %s, 错误原因: %s", errCode, errMsg));
                }
            } else {
                if (StringUtils.isEmpty(response.getString(Const.WECHAT_OPENID_KEY)))
                    throw new ServiceException("OpenId异常!");
                return response.getString(Const.WECHAT_OPENID_KEY);
            }
        }
        throw new ServiceException("获取OpenId失败,请检查!");
    }
}
