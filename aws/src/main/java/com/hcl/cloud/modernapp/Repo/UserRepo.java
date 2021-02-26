package com.hcl.cloud.modernapp.Repo;

import com.hcl.cloud.modernapp.domain.UserEntity;
import org.springframework.stereotype.Repository;

import com.hcl.cloud.modernapp.model.UserModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    public UserEntity findByUsername(String username);
}
