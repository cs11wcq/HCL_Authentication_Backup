package com.hcl.cloud.modernapp.services;

//We create SecurityService to provide current logged-in user and auto login user after registration
public interface UserSecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}