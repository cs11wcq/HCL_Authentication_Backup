package com.hcl.cloud.modernapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hcl.cloud.modernapp.Repo.UserRepo;
import com.hcl.cloud.modernapp.model.UserModel;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public UserModel findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
