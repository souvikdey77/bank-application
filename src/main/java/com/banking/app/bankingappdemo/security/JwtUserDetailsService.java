package com.banking.app.bankingappdemo.security;

import com.banking.app.bankingappdemo.model.UserDao;
import com.banking.app.bankingappdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Method to check if the user is present or not in db. If user is present, then
     * setting the email and password of the user in the User class provided by spring security
     *
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserDao user = userRepository.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("User not found with email : " + userName);
        }
        return new User(user.getUserName(),user.getPassword(),new ArrayList<>());
    }
}
