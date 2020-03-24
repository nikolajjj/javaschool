package com.tsystems.config;

import com.mysql.cj.protocol.AuthenticationProvider;
import com.tsystems.Util.Md5PasswordEncoder;
import com.tsystems.entity.User;
import com.tsystems.service.implementation.UserDetailsServiceImpl;
import com.tsystems.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Access;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(md5PasswordEncoder());
        return authProvider;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").access("permitAll()")
                /*.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/driver/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_DRIVER')")
                .antMatchers("/employee/**").access("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")*/
                .and().formLogin()
                .and().csrf().disable();
/*//                    .loginPage("/login")
                    .defaultSuccessUrl("/home")
                    .failureForwardUrl("/login?error")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and().logout()
                    .logoutSuccessUrl("/loginPage?logout")
                .and().csrf().disable();*/
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    }
}
