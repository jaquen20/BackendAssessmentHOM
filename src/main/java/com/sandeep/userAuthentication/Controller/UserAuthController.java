package com.sandeep.userAuthentication.Controller;

import com.sandeep.userAuthentication.JWTConfig.JwtHelper;
import com.sandeep.userAuthentication.Model.UserEntity;
import com.sandeep.userAuthentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Auth")
public class UserAuthController {
    @Autowired
    public JwtHelper jwtHelper;
    @Autowired
    public UserService userService;
    @Autowired
    public AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity userEntity){
        try {
            Authentication authentication= authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userEntity.getUsername(),userEntity.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserEntity users=userService.findUserByUsername(authentication.getName());

            final String token= jwtHelper.generateToken(users.getUsername());
            return ResponseEntity.ok("token " + token);
        }catch (BadCredentialsException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }
    @RequestMapping("/register")
    public ResponseEntity<?> RegisterUser(@RequestBody UserEntity userEntity){
        try{
            if (userService.findOptionalUserByUsername(userEntity.getUsername()).isPresent()) {
                return ResponseEntity.badRequest().body("email already exists");
            }
            userService.registerUser(userEntity);
            return ResponseEntity.ok("signup successful, now u can login again to access");
    }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("signup failed");

        }
    }

}
