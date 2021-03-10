package com.hcl.cloud.modernapp.controller;

import com.hcl.cloud.modernapp.Validator.Uservalidator;
import com.hcl.cloud.modernapp.model.UIResponse;
import com.hcl.cloud.modernapp.model.UserModel;
//import com.hcl.cloud.modernapp.services.UserSecurityService;
import com.hcl.cloud.modernapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserSecurityService securityService;

    @Autowired
    private Uservalidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public UIResponse register(@Valid @RequestBody UserModel body, BindingResult bindingResult) {
        userValidator.validate(body, bindingResult);
//        List<ObjectError> errorList = bindingResult.getAllErrors();
//        for (ObjectError error: errorList) {
//            if (error.equals())
//        }

        UIResponse response = new UIResponse();
        if (bindingResult.hasErrors()) {
            System.out.println("something is wrong");
            System.out.print(bindingResult);
            response.setStatusCode(400);
            // response.setStatusDescription("Invalid request: " + bindingResult.getAllErrors().toString());
//            return ResponseEntity.badRequest().eTag(bindingResult.getAllErrors().toString()).build();
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuffer sb = new StringBuffer();
            for (ObjectError error : errors ) {
                sb.append(error.getDefaultMessage());
            }
            response.setStatusDescription(sb.toString());
        }
        else {
            //error handling for duplicate registration.
            //appropriate status code to send back for duplicate entry invalid request
            userService.save(body);
            System.out.println("username: " + body.getUsername() + "  password: " + body.getPassword());

            response.setStatusCode(200);
            response.setStatusDescription("You registered successfully!");
        }

        return response;
    }


    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public UIResponse login(@Valid @RequestBody UserModel body) {
        UIResponse response = new UIResponse();
        try {
            UserModel model = userService.findByUsername(body.getUsername());
            System.out.println(model.getPassword());
            System.out.println(model.getUsername());
            // raw password vs encoded one
            if (bCryptPasswordEncoder.matches(body.getPassword(), model.getPassword())) {
                System.out.print("'correct'");
                response.setStatusCode(200);
                response.setStatusDescription("You logged in successfully!");
                return response;
            }
        } catch (Exception e) {
            System.out.print("'Invalid username'");
            System.out.print(body);

            response.setStatusCode(404); //not found
            response.setStatusDescription("Invalid username");

            return response;
            // Block of code to handle errors
        }
        System.out.print("valid username, but invalid password");
        response.setStatusCode(401); //unauthorized
        response.setStatusDescription("Your password is incorrect");
        return response;
    }

//    @GetMapping(value = "/login", consumes = "application/json", produces = "application/json")
//    public UIResponse login(@Valid @RequestBody UserModel user) {
//
//    }

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
