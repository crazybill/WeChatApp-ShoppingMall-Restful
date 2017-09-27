package com.leewaiho.togogo.module.sys.security.config;

import com.leewaiho.togogo.common.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Author leewaiho
 * Email 791783391@qq.com
 * Date 2017/9/23
 * Project togogo-shixun
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizeServerConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private TokenStore tokenStore;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(Const.CLIENT_ID)
                .secret(Const.CLIENT_SECRET)
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUST_CLIENT")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(86400)
                .refreshTokenValiditySeconds(6000);
    }
    
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
    }
    
    
}
