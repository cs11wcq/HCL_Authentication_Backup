package com.hcl.cloud.modernapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Once authenticated, user should be able to access /jwt
 */
@RestController
public class BusinessLogic {
    @RequestMapping({ "/jwt" })
    public String firstPage() {
        return "Welcome to the world of Spring Boot JWT";
    }
}
