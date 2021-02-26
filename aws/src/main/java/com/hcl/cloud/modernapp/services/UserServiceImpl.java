package com.hcl.cloud.modernapp.services;

import com.hcl.cloud.modernapp.domain.UserEntity;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(userEntity);
    }

    @Override
    public UserModel findByUsername(String username) {
        UserEntity userEntity = userRepo.findByUsername(username);
        UserModel userModel = new UserModel();
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        return userModel;
    }
}
