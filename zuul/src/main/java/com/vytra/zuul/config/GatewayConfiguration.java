/*
package com.vytra.zuul.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.BASIC_AUTH_ORDER)
//@EnableConfigurationProperties(Oauth2RequestSecurityProperties.class)
public class GatewayConfiguration extends ResourceServerConfigurerAdapter {

*/
/*    @Autowired
    private DataSource dataSource;*//*


   @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated();
    }

  @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("USER_ADMIN_RESOURCE").stateless(false);
    }


    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(
                "http://localhost:8763/oauth/check_token");
        tokenService.setClientId("USER_CLIENT_APP");
        tokenService.setClientSecret("password");
        return tokenService;
    }


*/
/*    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }*//*

}

*/
