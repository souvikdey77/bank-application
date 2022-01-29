package com.banking.app.bankingappdemo.service.impl;

import com.banking.app.bankingappdemo.model.UserDao;
import com.banking.app.bankingappdemo.pojo.JwtRequest;
import com.banking.app.bankingappdemo.pojo.SignUpTo;
import com.banking.app.bankingappdemo.repository.UserRepository;
import com.banking.app.bankingappdemo.security.JwtTokenUtil;
import com.banking.app.bankingappdemo.security.JwtUserDetailsService;
import com.banking.app.bankingappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String ACCOUNTINITIAL = "6275346272";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public UserDao createUser(SignUpTo signUpTo) {
        UserDao newUser = new UserDao();
        newUser.setUserName(signUpTo.getUserName());
        newUser.setPassword(bcryptEncoder.encode(signUpTo.getPassword()));
        newUser.setFirstName(signUpTo.getFirstName());
        newUser.setLastName(signUpTo.getLastName());
        Random random = new Random();
        String accountNumber = ACCOUNTINITIAL.concat(String.valueOf(random.nextInt(20)));
        newUser.setAccountNumber(accountNumber);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public String loginUser(JwtRequest jwtRequest) {
        String userName = jwtRequest.getUserName();
        String password = jwtRequest.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userName);
        return jwtTokenUtil.generateToken(userDetails);
    }
}
