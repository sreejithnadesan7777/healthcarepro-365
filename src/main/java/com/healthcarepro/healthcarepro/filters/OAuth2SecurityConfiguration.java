package com.healthcarepro.healthcarepro.filters;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(corsFilter(), SessionManagementFilter.class) //adds your custom CorsFilter
//                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
//                .formLogin()
//                .successHandler(ajaxSuccessHandler)
//                .failureHandler(ajaxFailureHandler)
//                .loginProcessingUrl("/authentication")
//                .passwordParameter("password")
//                .usernameParameter("username")
//                .and()
//                .logout()
//                .deleteCookies("JSESSIONID")
//                .invalidateHttpSession(true)
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .csrf().disable()
//                .anonymous().disable()
//                .authorizeRequests()
//                .antMatchers("/authentication").permitAll()
//                .antMatchers("/oauth/token").permitAll()
//                .antMatchers("/admin/*").access("hasRole('ROLE_ADMIN')")
//                .antMatchers("/user/*").access("hasRole('ROLE_USER')");
    }
}
