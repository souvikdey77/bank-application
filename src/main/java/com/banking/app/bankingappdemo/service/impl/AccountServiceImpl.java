package com.banking.app.bankingappdemo.service.impl;

import com.banking.app.bankingappdemo.model.Account;
import com.banking.app.bankingappdemo.model.TransactionDetail;
import com.banking.app.bankingappdemo.pojo.AccountStatement;
import com.banking.app.bankingappdemo.pojo.DepositBalanceTO;
import com.banking.app.bankingappdemo.pojo.TransferBalanceTO;
import com.banking.app.bankingappdemo.repository.AccountRepository;
import com.banking.app.bankingappdemo.repository.TransactionRepository;
import com.banking.app.bankingappdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

/**
 * @Souvik Dey
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    /**
     * Business implementation for transfer of money
     *
     * @param transferBalanceTO
     * @return TransactionDetail
     */
    @Override
    public TransactionDetail transferMoney(TransferBalanceTO transferBalanceTO) {

        String fromAccountNumber = transferBalanceTO.getFromAccountNumber();
        String toAccountNumber = transferBalanceTO.getToAccountNumber();
        BigDecimal transferAmount = transferBalanceTO.getAmount();
        Account fromAccount = accountRepository.findByAccountNumberEquals(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumberEquals(toAccountNumber);
        if(fromAccount.getTotalBalance().compareTo(BigDecimal.ONE) == 1
                && fromAccount.getTotalBalance().compareTo(transferAmount) == 1){
            fromAccount.setTotalBalance(fromAccount.getTotalBalance().subtract(transferAmount));
            toAccount.setTotalBalance(toAccount.getTotalBalance().add(transferAmount));
            accountRepository.save(toAccount);
            TransactionDetail transactionDetail = new TransactionDetail();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transactionDetail.setId(Math.abs(new Random().nextLong()));
            transactionDetail.setAccountNumber(fromAccountNumber);
            transactionDetail.setTransactionAmount(transferAmount);
            transactionDetail.setTransactionTime(timestamp);
            transactionRepository.save(transactionDetail);
            return transactionDetail;
        }
        return null;
    }

    /**
     * Business implementation of deposit money
     *
     * @param depositBalanceTO
     * @return
     */
    @Override
    public TransactionDetail depositMoney(DepositBalanceTO depositBalanceTO) {
        BigDecimal amountToDeposit = depositBalanceTO.getAmount();
        String accountNumber = depositBalanceTO.getAccountNumber();
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        if(account != null && account.getAccountNumber() != null){
            account.setTotalBalance(account.getTotalBalance().add(amountToDeposit));
            accountRepository.save(account);
            TransactionDetail transactionDetail = new TransactionDetail();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            transactionDetail.setId(Math.abs(new Random().nextLong()));
            transactionDetail.setTransactionAmount(amountToDeposit);
            transactionDetail.setAccountNumber(accountNumber);
            transactionDetail.setTransactionTime(timestamp);
            transactionRepository.save(transactionDetail);
            return transactionDetail;
        }
        return null;
    }

    /**
     * Business implementation of get the account statement
     *
     * @param accountNumber
     * @return
     */
    @Override
    public AccountStatement getStatement(String accountNumber) {
        Account account = accountRepository.findByAccountNumberEquals(accountNumber);
        List<TransactionDetail> transactionDetails = transactionRepository.findByAccountNumberEquals(accountNumber);
        AccountStatement accountStatement = new AccountStatement();
        accountStatement.setCurrentBalance(account.getTotalBalance());
        accountStatement.setTransactionHistory(transactionDetails);
        return accountStatement;
    }
}
