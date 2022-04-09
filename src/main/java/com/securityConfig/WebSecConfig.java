package com.securityConfig;

import com.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;

/**
 * Security configuration class
 * 
 * WebSecConfig.java
 * 
 * @version 1.0
 * 
 * @author samuel Maina
 * 
 * 12-23-2022
 */
@Configuration
@EnableWebSecurity

public class WebSecConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsServiceImpl userDetailService;

    @Autowired
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    //@Order(-100)
    //@Configuration

    

        private AuthEntryPointJwt unauthorizedHandler;

        @Bean
        public AuthTokenFilter authenticationJwtTokenFilter() {
            return new AuthTokenFilter();
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .antMatcher("/api/v1/**")
                    .authorizeRequests().antMatchers("/api/v1/mobile/milk_record/**").hasAuthority(Roles.milk_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/mobile/milk_record/**").hasAuthority(Roles.milk_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/mobile/sales**").hasAuthority(Roles.store_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/mobile/sales/**").hasAuthority(Roles.store_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/mobile/memberpurchases/vet/**").hasAuthority(Roles.store_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/deductions/**").hasAuthority(Roles.deductions_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/records/**").hasAuthority(Roles.milk_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/members/**").hasAuthority(Roles.member_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/users/**").hasAuthority(Roles.user_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/vet/**").hasAuthority(Roles.user_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/user-roles/**").hasAuthority(Roles.user_management.name()).and()
                    .authorizeRequests().antMatchers("/api/v1/purchases/**").hasAuthority(Roles.store_management.name());
            http.addFilterBefore((Filter) authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }


