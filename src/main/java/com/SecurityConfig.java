package com;

import com.service.AuthenticationProviderImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Donnie on 2017/2/22.
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private static AuthenticationProviderImp authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                    .antMatchers("/", "/login").permitAll()
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
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
//        auth.authenticationProvider(authenticationProvider);
        auth.authenticationProvider(new AuthenticationProviderImp());

        log.info("configure(AuthenticationManagerBuilder auth), auth={}",auth);
    }
}
