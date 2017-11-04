package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created by asheeshdwivedi on 2/4/16.
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter{



    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient("test-app").secret("test-app")
                .authorizedGrantTypes("authorization_code" ,"implicit")
                .scopes("read", "write", "trust").autoApprove(true).
        and().withClient("spring-security-angular")
                .authorizedGrantTypes("authorization_code" ,"implicit")
                .scopes("read", "write", "trust");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenServices(defaultTokenService())
                .userApprovalHandler(userApprovalHandler())
                .authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
    }

   /* @Bean
    public TokenKeyEndpoint tokenKeyEndpoint(){
        TokenKeyEndpoint endpoint = new TokenKeyEndpoint(jwtAccessTokenConverter());
        return endpoint;
    }*/

    @Bean
    public AuthorizationServerTokenServices defaultTokenService(){
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setAuthenticationManager(authenticationManager);
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter());

        return tokenServices;
    }
    @Bean
    public TokenStore tokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("1234");
        return  jwtAccessTokenConverter;
    }

    @Bean
    public UserApprovalHandler userApprovalHandler(){
        TokenStoreUserApprovalHandler tokenStoreUserApprovalHandler = new TokenStoreUserApprovalHandler();
        tokenStoreUserApprovalHandler.setTokenStore(tokenStore());
        tokenStoreUserApprovalHandler.setRequestFactory(oAuth2RequestFactory());
        tokenStoreUserApprovalHandler.setClientDetailsService(clientDetailsService);
        return tokenStoreUserApprovalHandler;
    }

    @Bean
    public OAuth2RequestFactory oAuth2RequestFactory(){
        OAuth2RequestFactory oAuth2RequestFactory = new DefaultOAuth2RequestFactory(clientDetailsService);
        return  oAuth2RequestFactory;
    }
}
