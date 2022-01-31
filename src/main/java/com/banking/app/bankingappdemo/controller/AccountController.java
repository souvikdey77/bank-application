package com.banking.app.bankingappdemo.controller;

import com.banking.app.bankingappdemo.exception.DepositAmountException;
import com.banking.app.bankingappdemo.exception.StatementNotFoundException;
import com.banking.app.bankingappdemo.exception.TransferAmountException;
import com.banking.app.bankingappdemo.model.Account;
import com.banking.app.bankingappdemo.model.TransactionDetail;
import com.banking.app.bankingappdemo.pojo.AccountStatement;
import com.banking.app.bankingappdemo.pojo.DepositBalanceTO;
import com.banking.app.bankingappdemo.pojo.TransferBalanceTO;
import com.banking.app.bankingappdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Souvik Dey
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * Method of transferring the amount
     *
     * @param account
     * @return account
     * @throws Exception
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> create(@RequestBody @Valid Account account) throws Exception {
        Account newAccount =  accountService.create(account);
        return ResponseEntity.ok(newAccount);
    }

    /**
     * Method of transferring the amount
     *
     * @param userName
     * @return account
     * @throws Exception
     */
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/fetchAccountNumber/{username}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fetchAccount(@PathVariable("username") String userName) throws Exception {
        String accountNumber =  accountService.fetchAccountNumber(userName);
        return ResponseEntity.ok(accountNumber);
    }

    /**
     * Method of transferring the amount
     *
     * @param transferBalanceTO
     * @return TransactionDetail
     * @throws Exception
     */
    @PutMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetail> transferAmount(@RequestBody @Valid TransferBalanceTO transferBalanceTO) throws Exception {
       TransactionDetail transactionDetail =  accountService.transferMoney(transferBalanceTO);
       if(transactionDetail != null){
           return ResponseEntity.ok(transactionDetail);
       }else{
           throw new TransferAmountException("Exception occurred while transfer money from the account " + transactionDetail.getAccountNumber());
       }
    }

    /**
     * Method of depositing amount
     *
     * @param depositTo
     * @return TransactionDetail
     */
    @PutMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDetail> depositAmount(@RequestBody @Valid DepositBalanceTO depositTo){
        TransactionDetail transactionDetail = accountService.depositMoney(depositTo);
        if(transactionDetail != null){
            return ResponseEntity.ok(transactionDetail);
        }else{
            throw new DepositAmountException("Exception occurred while depositing money with the account " + transactionDetail.getAccountNumber());
        }
    }

    /**
     * Method of getting the bank statement for a given accountnumber
     *
     * @param accountNumber
     * @return AccountStatement
     */
    @GetMapping(value = "/statement/{accountnumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountStatement> getStatement(@PathVariable("accountnumber") String accountNumber){
        AccountStatement accountStatement = accountService.getStatement(accountNumber);
        if(accountStatement != null){
            return ResponseEntity.ok(accountStatement);
        }else{
            throw new StatementNotFoundException("Exception occurred while fetching statement for accountnumber " + accountNumber);
        }
    }
}
