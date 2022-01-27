package br.com.api.stock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String URI_PRODUCTS = "/products/**";
    private static final String URI_MOVEMENTS = "/movements/**";
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USERS = "USERS";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}user123").roles(ROLE_USERS)
                .and()
                .withUser("admin").password("{noop}admin123").roles(ROLE_USERS,ROLE_ADMIN);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, URI_PRODUCTS).hasRole(ROLE_USERS)
                .antMatchers(HttpMethod.POST, URI_PRODUCTS).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, URI_PRODUCTS).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, URI_PRODUCTS).hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, URI_MOVEMENTS).hasRole(ROLE_ADMIN)
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}