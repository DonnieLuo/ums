package com;

import com.handler.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by Donnie on 2017/6/18.
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**","/images/**","/js/**","/vendors/**","/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/", true).permitAll()
                .usernameParameter("username").passwordParameter("password").successHandler(new LoginSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll().logoutUrl("/logout").logoutSuccessUrl("/login")
                .deleteCookies(new String[] { "cookie" })
                .and()
                .rememberMe();
    }

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
//    @Bean
//    public EntityManager entityManager() {
//        return entityManagerFactory().getObject().createEntityManager();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setPersistenceProviderClass(org.apache.openjpa.persistence.PersistenceProviderImpl);
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.repository");
//        return em;
//    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {


        DataSourceBuilder builder = new DataSourceBuilder(this.getClass().getClassLoader());
        auth.jdbcAuthentication().dataSource(dataSource())//todo .passwordEncoder()
                .authoritiesByUsernameQuery("select username, 1 from user where username=?")//all avilable
                .usersByUsernameQuery("select username, password, 1 from user where username=?");//all
    }


}
