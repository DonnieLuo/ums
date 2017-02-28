package com;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Donnie on 2017/2/22.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .usernameParameter("username").passwordParameter("password")
                    .loginProcessingUrl("/login").loginPage("/login")
                    .and()
//                .logout().permitAll().logoutUrl("/logout").logoutSuccessUrl("/login")
//                    .logoutSuccessHandler(logoutSuccessHandler)
//                    .invalidateHttpSession(true).addLogoutHandler(logoutHandler).deleteCookies(new String[] { "cookie名字" })
                   .logout()
                        .permitAll()
                    .and()
                .rememberMe();
    }
}
