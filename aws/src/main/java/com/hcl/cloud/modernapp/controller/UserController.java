package com.hcl.cloud.modernapp.controller;

import com.hcl.cloud.modernapp.Validator.Uservalidator;
import com.hcl.cloud.modernapp.model.UserModel;
import com.hcl.cloud.modernapp.services.UserSecurityService;
import com.hcl.cloud.modernapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService securityService;

    @Autowired
    private Uservalidator userValidator;

    // @CrossOrigin(origins = "http://localhost:4200")
    // @GetMapping("/registration")
    // public String registration(Model model) {
    // model.addAttribute("userForm", new UserModel());

    // return "registration";
    // }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserModel userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "error";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "login";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
            return "error";
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
            return "error";
        }

        return "login";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/")
    public String welcome(Model model) {
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
