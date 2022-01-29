package com.banking.app.bankingappdemo.repository;

import com.banking.app.bankingappdemo.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetail, Long> {
    List<TransactionDetail> findByAccountNumberEquals(String accountNumber);
}
