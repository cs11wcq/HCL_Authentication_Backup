package com.hcl.cloud.modernapp.Validator;

import com.hcl.cloud.modernapp.model.UserModel;
import com.hcl.cloud.modernapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 */
@Service
public class Uservalidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.equals(aClass);
    }
    /*
    note: user reject not rejectValue to make the error global (accessible from controller)
    https://stackoverflow.com/questions/44755789/spring-validation-how-to-retrieve-errors-rejectvalue-in-my-contoller
     */
    @Override
    public void validate(Object o, Errors errors) {
        UserModel inputUser = (UserModel) o; //from whatever is typed in
        UserModel user = userService.findByUsername(inputUser.getUsername()); //user from the database
        if (user != null) {
            //if the username already exists
            //Since I am using a Rest, I want to capture the error event and send back an appropriate response to my front end.
            errors.reject("username", "There is already an account with that username");
        }


        if (inputUser.getUsername().contains(" ")) {
            errors.reject("username", "space");
        }

        if (inputUser.getUsername().length() < 6 || inputUser.getUsername().length() > 32) {
            errors.reject("username", "username must be between 6 and 32 chars");
        }

        if (inputUser.getPassword().contains(" ")) {
            errors.reject("password", "space");
        }

        if (inputUser.getPassword().length() < 6 || inputUser.getPassword().length() > 32) {
            errors.reject("password", "password must be between 6 and 32 chars");
        }

    }
}
