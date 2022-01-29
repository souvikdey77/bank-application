package com.banking.app.bankingappdemo.service;

import com.banking.app.bankingappdemo.model.UserDao;
import com.banking.app.bankingappdemo.pojo.JwtRequest;
import com.banking.app.bankingappdemo.pojo.SignUpTo;

public interface UserService {
    UserDao createUser(SignUpTo signUpTo);
    String loginUser(JwtRequest jwtRequest);
}
