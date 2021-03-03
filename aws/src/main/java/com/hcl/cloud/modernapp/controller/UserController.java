package com.hcl.cloud.modernapp.controller;

import com.hcl.cloud.modernapp.Validator.Uservalidator;
import com.hcl.cloud.modernapp.model.UserModel;
import com.hcl.cloud.modernapp.services.UserSecurityService;
import com.hcl.cloud.modernapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService securityService;

    @Autowired
    private Uservalidator userValidator;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/registration")
    public String registration(@RequestParam(required = false, defaultValue = "World") String name) {
        System.out.println("This is the debugging code: " + name);
        userValidator.validate(name, null);
//        userValidator.validate(userForm, null);
//
//        userService.save(userForm);
//
//        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String welcome() {
        return "welcome";
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
