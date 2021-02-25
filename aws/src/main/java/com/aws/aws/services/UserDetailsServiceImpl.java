package com.aws.aws.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import com.aws.aws.Repo.UserRepo;
import com.aws.aws.model.UserModel;

/*
UserDetailsService interface is used in order to lookup the username, password and GrantedAuthorities 
for any given user.
This interface provide only one method which implementing class need to implement-loadUserByUsername()
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // for (Role role : user.getRoles()){
        // grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        // }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                grantedAuthorities);
    }
}
