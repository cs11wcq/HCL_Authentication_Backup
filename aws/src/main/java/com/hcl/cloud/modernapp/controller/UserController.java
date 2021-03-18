package com.hcl.cloud.modernapp.controller;

import com.hcl.cloud.modernapp.Validator.Uservalidator;
import com.hcl.cloud.modernapp.config.JwtTokenUtil;
import com.hcl.cloud.modernapp.model.*;
//import com.hcl.cloud.modernapp.services.UserSecurityService;
import com.hcl.cloud.modernapp.services.JwtUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Uservalidator userValidator;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserModel> register(@Valid @RequestBody UserEntity body) {

        return ResponseEntity.ok(jwtUserDetailsService.save(body));
//        userValidator.validate(body, bindingResult);
//
//        UIResponse response = new UIResponse();
//        if (bindingResult.hasErrors()) {
//            //error handling for duplicate registration.
//            //appropriate status code to send back for duplicate entry invalid request
//            System.out.println("something is wrong");
//            System.out.print(bindingResult);
//            response.setStatusCode(400);
//            // response.setStatusDescription("Invalid request: " + bindingResult.getAllErrors().toString());
////            return ResponseEntity.badRequest().eTag(bindingResult.getAllErrors().toString()).build();
//            List<ObjectError> errors = bindingResult.getAllErrors();
//            StringBuffer sb = new StringBuffer();
//            for (ObjectError error : errors ) {
//                sb.append(error.getDefaultMessage());
//            }
//            response.setStatusDescription(sb.toString());
//        }
//        else {
//            jwtUserDetailsService.save(body);
//            System.out.println("username: " + body.getUsername() + "  password: " + body.getPassword());
//
//            response.setStatusCode(200);
//            response.setStatusDescription("You registered successfully!");
//        }
//
//        return response;
    }
    @PostMapping(value = "/authenticate", consumes = "application/json", produces = "application/json")
    public UIResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        UIResponse response = new UIResponse();
        // Perform the security
        /*
         this method receives the user’s credentials then checks them using an implementation of the AuthenticationManager Interface,
        if all is correct the user get a token from the server. else, he will receive an unauthorized message.
         */
        //todo: not being used?
        try {
            final Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            response.setStatusDescription(e.getMessage());
        }


        try {
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

            // raw password vs encoded one
            if (bCryptPasswordEncoder.matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
                System.out.print("'correct'");
                response.setStatusCode(200);
                response.setStatusDescription("You logged in successfully!");
                final String token = jwtTokenUtil.generateToken(userDetails);
                response.setToken(token);
                return response;
            }
        } catch (Exception e) {
            System.out.print("'Invalid username'");
            System.out.print(authenticationRequest);

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


//    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
//    public UIResponse login(@Valid @RequestBody UserModel body) {
//        UIResponse response = new UIResponse();
//        try {
//            UserModel model = userService.loadUserByUsername(body.getUsername());
//            System.out.println(model.getPassword());
//            System.out.println(model.getUsername());
//            // raw password vs encoded one
//            if (bCryptPasswordEncoder.matches(body.getPassword(), model.getPassword())) {
//                System.out.print("'correct'");
//                response.setStatusCode(200);
//                response.setStatusDescription("You logged in successfully!");
//                return response;
//            }
//        } catch (Exception e) {
//            System.out.print("'Invalid username'");
//            System.out.print(body);
//
//            response.setStatusCode(404); //not found
//            response.setStatusDescription("Invalid username");
//
//            return response;
//            // Block of code to handle errors
//        }
//        System.out.print("valid username, but invalid password");
//        response.setStatusCode(401); //unauthorized
//        response.setStatusDescription("Your password is incorrect");
//        return response;
//    }



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
