package com.sandeep.userAuthentication.Service;

import com.sandeep.userAuthentication.Model.UserEntity;
import com.sandeep.userAuthentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity findUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional=userRepository.findByUsername(username);

        return userEntityOptional.orElse(null);
    }
    public Optional<UserEntity> findOptionalUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional=userRepository.findByUsername(username);

        return userRepository.findByUsername(username) ;
    }
    public void registerUser(UserEntity userEntity) {
        UserEntity userEntity1=new UserEntity();
        userEntity1.setUsername(userEntity.getUsername());
        userEntity1.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity1);
    }
}
