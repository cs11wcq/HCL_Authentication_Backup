package com.aws.aws.Repo;

import org.springframework.stereotype.Repository;

import com.aws.aws.model.UserModel;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepo extends CrudRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
