package com.banking.app.bankingappdemo.controller;

import com.banking.app.bankingappdemo.exception.IncorrectCredentialException;
import com.banking.app.bankingappdemo.model.UserDao;
import com.banking.app.bankingappdemo.pojo.JwtRequest;
import com.banking.app.bankingappdemo.pojo.JwtResponse;
import com.banking.app.bankingappdemo.pojo.SignUpTo;
import com.banking.app.bankingappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Souvik Dey
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Method for creating an user
     *
     * @param signUpTo
     * @return UserDao
     */
    @PostMapping(value = "/signup" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDao> signUpUser(@RequestBody @Valid SignUpTo signUpTo){
        UserDao user =  userService.createUser(signUpTo);
        return ResponseEntity.ok(user);
    }

    /**
     * Method for validating an user
     *
     * @param jwtRequest
     * @return JwtResponse
     */
    @PostMapping(value = "/signin" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtResponse> signInUser(@RequestBody @Valid JwtRequest jwtRequest){
        String token = null;
        try{
             token = userService.loginUser(jwtRequest);
        }catch(BadCredentialsException e){
            throw new IncorrectCredentialException("INVALID CREDENTIALS");
        }
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
