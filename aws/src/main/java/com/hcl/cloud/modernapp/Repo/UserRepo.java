package com.aws.aws.Repo;

import org.springframework.stereotype.Repository;

import com.aws.aws.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
