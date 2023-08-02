package com.phamtranduc.bookStorePTD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ConfigSecurity {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_name, pass_word,1 FROM user where user_name=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT u.user_name, r.name FROM user u JOIN user_role ur ON u.id = ur.user_id" +
                " JOIN role r ON ur.role_id = r.id WHERE u.user_name=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws  Exception{
        httpSecurity.authorizeRequests(configure-> configure
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
        )
                .formLogin(form->form
                        .loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .exceptionHandling(configure->configure
                        .accessDeniedPage("/deniedPage")
                ).logout(logout-> logout.permitAll());
        return httpSecurity.build();
    }
    
    
}
