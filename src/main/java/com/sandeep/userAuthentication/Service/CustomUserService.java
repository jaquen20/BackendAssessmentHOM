package com.sandeep.userAuthentication.Service;

import com.sandeep.userAuthentication.Model.UserEntity;
import com.sandeep.userAuthentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user with username: "+username+" not found"));
        return  new org.springframework.security.core.userdetails.User(userEntity.getUsername(),userEntity.getPassword(),new ArrayList<>());

    }
}
