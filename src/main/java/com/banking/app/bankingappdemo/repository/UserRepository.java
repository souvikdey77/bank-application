package com.banking.app.bankingappdemo.repository;

import com.banking.app.bankingappdemo.model.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {
    UserDao findByUserName(String userName);
}
