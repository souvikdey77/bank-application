package com.banking.app.bankingappdemo.service;

import com.banking.app.bankingappdemo.model.Account;
import com.banking.app.bankingappdemo.model.TransactionDetail;
import com.banking.app.bankingappdemo.pojo.AccountStatement;
import com.banking.app.bankingappdemo.pojo.DepositBalanceTO;
import com.banking.app.bankingappdemo.pojo.TransferBalanceTO;

public interface AccountService {
    Account create(Account account);
    TransactionDetail transferMoney(TransferBalanceTO transferBalanceTO);
    TransactionDetail depositMoney(DepositBalanceTO depositBalanceTO);
    AccountStatement getStatement(String accountNumber);
}
