package com.hcl.cloud.modernapp.Validator;

import com.hcl.cloud.modernapp.model.UserModel;
import com.hcl.cloud.modernapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class Uservalidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserModel user = (UserModel) o;

        if (user.getUsername().contains(" ")) {
            errors.rejectValue("username", "space");
        }

        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "too big");
        }

        if (user.getPassword().contains(" ")) {
            errors.rejectValue("password", "space");
        }

        if (user.getPassword().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("password", "too big");
        }

    }
}
