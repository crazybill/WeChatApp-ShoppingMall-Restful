package com.leewaiho.togogo.common.util;

import com.leewaiho.togogo.common.Const;
import com.leewaiho.togogo.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.Arrays;

import static com.leewaiho.togogo.common.Const.CLIENT_ID;
import static com.leewaiho.togogo.common.Const.CLIENT_SECRET;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/27
 * Project togogo-shixun
 */
public class OAuth2Util {
    
    private static final Logger log = LoggerFactory.getLogger(OAuth2Util.class);
    
    public static OAuth2AccessToken getAccessToken(String username, String password) {
        //创建一个包含需要请求的资源实体以及认证信息集合的对象
        ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        //设置请求认证授权的服务器的地址
        details.setAccessTokenUri(SpringContextUtil.getParamVal(Const.OAUTH_TOKEN_URI));
        //下面都是认证信息：所拥有的权限，认证的客户端，具体的用户
        details.setScope(Arrays.asList("read", "write", "trust"));
        details.setClientId(CLIENT_ID);
        details.setClientSecret(CLIENT_SECRET);
        details.setUsername(username);
        details.setPassword(password);
        
        ResourceOwnerPasswordAccessTokenProvider provider = new ResourceOwnerPasswordAccessTokenProvider();
        OAuth2AccessToken accessToken;
        try {
            // 获取AccessToken
            // 1、(内部流程简介：根据上述信息，将构造一个前文一中的请求头为 "Basic Base64(username:password)" 的http请求
            // 2、之后将向认证授权服务器的 oauth/oauth_token 端点发送请求，试图获取AccessToken
            accessToken = provider.obtainAccessToken(details, new DefaultAccessTokenRequest());
        } catch (NullPointerException e) {
            log.error("授权失败原因：{}", e.getMessage());
            throw new ServiceException("用户不存在");
        } catch (Exception e) {
            if (e instanceof OAuth2AccessDeniedException) {
                OAuth2AccessDeniedException accessDeniedException = (OAuth2AccessDeniedException) e;
                log.error("授权失败原因：{}", accessDeniedException.getMessage());
                throw new ServiceException(Const.ServiceCode.LOGIN_FAILED);
            }
            e.printStackTrace();
            log.error("授权失败原因：{}", e.getMessage());
            throw new ServiceException("创建token失败");
        }
        return accessToken;
    }
}
