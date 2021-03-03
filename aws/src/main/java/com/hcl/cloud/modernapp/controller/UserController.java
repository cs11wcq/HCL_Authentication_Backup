package com.hcl.cloud.modernapp.controller;

import com.hcl.cloud.modernapp.Validator.Uservalidator;
import com.hcl.cloud.modernapp.model.UserModel;
import com.hcl.cloud.modernapp.services.UserSecurityService;
import com.hcl.cloud.modernapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService securityService;

    @Autowired
    private Uservalidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> register(@RequestBody UserModel body, BindingResult bindingResult) {
        userValidator.validate(body, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println("something is wrong");
            System.out.print(bindingResult);
            return ResponseEntity.notFound().build();
        }

        userService.save(body);
        System.out.println("username: " + body.getUsername() + "  password: " + body.getPassword());

        return new ResponseEntity<>(body, HttpStatus.OK);
        // userService.

        // return new UserModel();
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> login(@RequestBody UserModel body) {

        try {
            UserModel model = userService.findByUsername(body.getUsername());
            System.out.println(model.getPassword());
            System.out.println(model.getUsername());
            // Block of code to try
            if (bCryptPasswordEncoder.matches(body.getPassword(), model.getPassword())) {
                System.out.print("'correct'");
                return new ResponseEntity<>(body, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.print("'incorrect'");
            System.out.print(body);

            return ResponseEntity.notFound().build();
            // Block of code to handle errors
        }
        System.out.print("incorrect");
        return null;
    }

    @GetMapping("/")
    public String welcome() {
        System.out.print("yo");
        return "Welcome";
    }

    @PostMapping("/")
    public String welcome(@RequestBody String nParam) {
        System.out.print("'were getting anew message'" + nParam);
        return nParam;
    }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @GetMapping("/register")
    // public UserModel addUser(@RequestBody UserModel userModel) {
    // String rawPass = userModel.getPassword();
    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // String encodedPassword = encoder.encode(rawPass);
    // UserModel safeUser = new UserModel(userModel.getUsername(), encodedPassword);
    // return userService.addUser(safeUser);
    // }

    // @CrossOrigin(origins = "http://localhost:4200")
    // @GetMapping("/login")
    // public ResponseEntity<Boolean> login(@RequestBody UserModel userModel) {
    // // if (userService.authenticate(userModel)) {
    // // return ResponseEntity.ok(true);
    // // } else {
    // // }
    // return ResponseEntity.ok(false);
    // }

    // @Configuration
    // @EnableWebSecurity
    // class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.authorizeRequests().anyRequest().authenticated();

    // http.formLogin();
    // http.logout();
    // }
    // }

    // AWS Pricing Things

}
