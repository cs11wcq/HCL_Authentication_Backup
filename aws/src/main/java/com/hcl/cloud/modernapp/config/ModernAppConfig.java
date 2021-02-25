package com.hcl.cloud.modernapp.config;

import com.hcl.cloud.modernapp.Repo.UserRepo;
import com.hcl.cloud.modernapp.services.UserSecurityService;
import com.hcl.cloud.modernapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ModernAppConfig {
    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserSecurityService userSecurityService;

    @Autowired
    UserService userService;
}
