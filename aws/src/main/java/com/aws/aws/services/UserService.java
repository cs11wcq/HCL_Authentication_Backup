package com.aws.aws.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.aws.aws.Repo.UserRepo;
import com.aws.aws.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
// import com.hellokoding.auth.model.Role;
// import com.hellokoding.auth.model.User;
// import com.aws.aws.auth.repository.userRepositorysitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserModel addUser(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // for (Role role : user.get().getRoles()){
        // grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        // }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities);
    }

}
