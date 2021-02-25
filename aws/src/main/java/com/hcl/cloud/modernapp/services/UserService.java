package com.aws.aws.services;

import com.aws.aws.model.UserModel;

public interface UserService {

    void save(UserModel user);

    UserModel findByUsername(String username);

}
