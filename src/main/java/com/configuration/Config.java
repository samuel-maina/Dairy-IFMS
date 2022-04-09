package com.configuration;

import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author samuel
 */
@Configuration
public class Config {
    private UserRepository repo;

    @Bean
    public UserService userService_2() {
        return new UserService();
    }

    @Bean
    public UserRepository userRepos_2(UserRepository repo) {
        return repo;
    }

}
